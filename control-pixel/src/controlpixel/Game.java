package controlpixel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlpixel.screen.MainMenu;
import controlpixel.screen.Screen;
import controlpixel.screen.SelectLanguage;
import controlpixel.strings.StringError;
import controlpixel.strings.StringGame;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private final String VERSION;

	private final JFrame frame;

	private final int WIDTH;
	private final int HEIGHT;
	private final double SCREEN_RATIO;

	private int windowWidth;
	private int windowHeight;

	private boolean isFullscreen;
	private boolean updateFullscreen;

	private int rendererX;
	private int rendererY;
	private int rendererWidth;
	private int rendererHeight;

	private final BufferedImage renderer;

	private int fps;
	private boolean showFPS;

	private GameStatus gameStatus;

	private Screen selectLanguage;
	private Screen mainMenu;

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);

		this.VERSION = "0.1";

		this.WIDTH = 800;
		this.HEIGHT = 451;
		this.SCREEN_RATIO = (double) this.WIDTH / (double) this.HEIGHT;

		this.windowWidth = this.WIDTH;
		this.windowHeight = this.HEIGHT;

		this.rendererX = 0;
		this.rendererY = 0;
		this.rendererWidth = WIDTH;
		this.rendererHeight = HEIGHT;

		this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));

		this.frame = new JFrame();

		this.frame.setTitle(StringGame.TITLE.getValue());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		this.renderer = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.isFullscreen = false;
		this.updateFullscreen = false;

		this.showFPS = false;

		this.updateGameStatus(GameStatus.SELECT_LANGUAGE);
		this.initializeScreen();
	}

	public String getVERSION() {
		return VERSION;
	}

	public int getGameWidth() {
		return this.WIDTH;
	}

	public int getGameHeight() {
		return this.HEIGHT;
	}

	public int getRendererX() {
		return this.rendererX;
	}

	public int getRendererY() {
		return this.rendererY;
	}

	public int getRendererWidth() {
		return this.rendererWidth;
	}

	public int getRendererHeight() {
		return this.rendererHeight;
	}

	public boolean isFullscreen() {
		return this.isFullscreen;
	}

	public void updateGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void initializeScreen() {
		this.selectLanguage = new SelectLanguage(this);
		this.mainMenu = new MainMenu(this);
	}

	private void toggleFullscreen() {
		if (this.updateFullscreen) {
			this.frame.dispose();

			if (this.isFullscreen) {
				this.windowWidth = this.WIDTH;
				this.windowHeight = this.HEIGHT;

				frame.setUndecorated(false);
			} else {
				this.windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				this.windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

				frame.setUndecorated(true);
			}

			this.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
			this.setScreenRatio();

			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			this.requestFocus();

			this.isFullscreen = !this.isFullscreen;
			this.updateFullscreen = false;
		}
	}

	private void setScreenRatio() {
		this.rendererWidth = (int) (this.windowHeight * this.SCREEN_RATIO);

		if (this.rendererWidth > this.windowWidth) {
			this.rendererWidth = this.windowWidth;
			this.rendererHeight = (int) (this.windowWidth * this.SCREEN_RATIO);

			this.rendererX = 0;
			this.rendererY = (this.windowHeight - this.rendererHeight) / 2;
		} else {
			this.rendererHeight = this.windowHeight;

			this.rendererX = (this.windowWidth - this.rendererWidth) / 2;
			this.rendererY = 0;
		}
	}

	private void tick() {
		this.toggleFullscreen();

		if (this.gameStatus == GameStatus.MAIN_MENU) {
			this.mainMenu.tick();
		} else if (this.gameStatus == GameStatus.SELECT_LANGUAGE) {
			this.selectLanguage.tick();
		} else if (this.gameStatus == GameStatus.EXIT) {
			Game.exitGame();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.WIDTH, this.HEIGHT);

		if (this.gameStatus == GameStatus.MAIN_MENU) {
			this.mainMenu.render(render);
		} else if (this.gameStatus == GameStatus.SELECT_LANGUAGE) {
			this.selectLanguage.render(render);
		}

		if (this.showFPS) {
			render.setColor(Color.BLACK);
			render.fillRect(this.WIDTH - 120, 10, 110, 30);

			render.setColor(Color.WHITE);
			render.setFont(Util.getFontDefault());
			render.drawString(String.format("FPS: %d", this.fps), this.WIDTH - 115, 32);

			render.setColor(Color.WHITE);
			render.drawRect(this.WIDTH - 120, 10, 110, 30);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.windowWidth, this.windowHeight);

		graphics.drawImage(this.renderer, this.rendererX, this.rendererY, this.rendererWidth, this.rendererHeight, null);

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		int frames = 0;

		while (true) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();

				this.fps = frames;
				frames = 0;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Code
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			this.updateFullscreen = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Code
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Code
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.gameStatus == GameStatus.MAIN_MENU) {
			this.mainMenu.mousePressed(e);
		} else if (this.gameStatus == GameStatus.SELECT_LANGUAGE) {
			this.selectLanguage.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.gameStatus == GameStatus.MAIN_MENU) {
			this.mainMenu.mouseReleased(e);
		} else if (this.gameStatus == GameStatus.SELECT_LANGUAGE) {
			this.selectLanguage.mouseReleased(e);
		}
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR.getValue(), JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}
