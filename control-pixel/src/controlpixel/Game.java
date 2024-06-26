package controlpixel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlpixel.scenarios.Scenario;
import controlpixel.screen.Credits;
import controlpixel.screen.Exit;
import controlpixel.screen.MainMenu;
import controlpixel.screen.Pause;
import controlpixel.screen.Screen;
import controlpixel.screen.SelectLanguage;
import controlpixel.screen.SelectLevel01;
import controlpixel.screen.SelectLevel02;
import controlpixel.strings.StringError;
import controlpixel.strings.StringGame;
import controlpixel.util.Audio;
import controlpixel.util.Camera;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

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
	private GameStatus lastGameStatus;

	private final List<Screen> screens;

	private Scenario scenario;
	private Scenario scenarioBase;

	private boolean canRestartScenario;

	private boolean enableAudio;
	private Audio audioNow;

	private final Audio audioMenu;
	private final Audio audioGame;

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);

		this.VERSION = "1.1";

		this.WIDTH = 800;
		this.HEIGHT = 450;
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

		try {
			Image imageIcon = ImageIO.read(Game.class.getResource("/sprites/icon.png"));

			frame.setIconImage(imageIcon);
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		this.renderer = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.isFullscreen = false;
		this.updateFullscreen = false;

		this.showFPS = false;

		this.screens = new ArrayList<>();

		this.canRestartScenario = false;

		this.enableAudio = true;

		this.audioMenu = new Audio("/audios/menu.wav");
		this.audioGame = new Audio("/audios/game.wav");

		this.updateAudio(this.audioMenu);

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

	public Graphics getRender() {
		return this.renderer.getGraphics();
	}

	public boolean isFullscreen() {
		return this.isFullscreen;
	}

	public GameStatus getLastGameStatus() {
		return this.lastGameStatus;
	}

	public void updateGameStatus(GameStatus gameStatus) {
		this.lastGameStatus = this.gameStatus;
		this.gameStatus = gameStatus;

		if (this.gameStatus == GameStatus.RUN || this.gameStatus == GameStatus.PAUSE || (this.lastGameStatus == GameStatus.PAUSE && this.gameStatus == GameStatus.EXIT)) {
			this.updateAudio(this.audioGame);
		} else {
			this.updateAudio(this.audioMenu);
		}
	}

	public void initializeScreen() {
		this.screens.clear();

		this.screens.add(new MainMenu(this));
		this.screens.add(new Pause(this));
		this.screens.add(new SelectLevel01(this));
		this.screens.add(new SelectLevel02(this));
		this.screens.add(new Credits(this));
		this.screens.add(new Exit(this));
		this.screens.add(new SelectLanguage(this));
	}

	public void initializeScenario(Scenario scenario) {
		Camera.x = 0;
		Camera.y = 0;

		this.scenarioBase = scenario;
		this.canRestartScenario = true;
	}

	private void updateAudio(Audio audio) {
		if (this.audioNow != audio) {
			if (this.audioNow != null) {
				this.audioNow.stop();
			}

			this.audioNow = audio;
		}
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
		if (this.enableAudio) {
			this.audioNow.play();
		} else {
			this.audioNow.stop();
		}

		this.toggleFullscreen();

		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.tick();
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.tick();
					break;
				}
			}
		}

		if (this.canRestartScenario) {
			Camera.x = 0;
			Camera.y = 0;

			this.scenario = this.scenarioBase;
			this.canRestartScenario = false;
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

		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.render(render);
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.render(render);
					break;
				}
			}
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
		Util.loadData();

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
		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.keyReleased(e);
		} else if (this.gameStatus == GameStatus.PAUSE && (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
			this.updateGameStatus(GameStatus.RUN);
		}

		if (e.getKeyCode() == KeyEvent.VK_F2) {
			this.updateFullscreen = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			this.enableAudio = !this.enableAudio;
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
		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.mousePressed(e);
		} else {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.mousePressed(e);
					break;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.gameStatus != GameStatus.RUN) {
			for (Screen screen : this.screens) {
				if (screen.getGameStatus() == this.gameStatus) {
					screen.mouseReleased(e);
					break;
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.mouseWheelMoved(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (this.gameStatus == GameStatus.RUN) {
			this.scenario.mouseMoved(e);
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
