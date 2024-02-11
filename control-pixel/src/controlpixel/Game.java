package controlpixel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlpixel.strings.StringError;
import controlpixel.strings.StringGame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private final JFrame frame;

	private final static int WIDTH;
	private final static int HEIGHT;

	private static int RENDERER_X;
	private static int RENDERER_Y;
	private static int RENDERER_WIDTH;
	private static int RENDERER_HEIGHT;

	private final BufferedImage renderer;

	private int fps;
	private boolean showFPS;

	static {
		WIDTH = 800;
		HEIGHT = 451;

		RENDERER_X = 0;
		RENDERER_Y = 0;
		RENDERER_WIDTH = WIDTH;
		RENDERER_HEIGHT = HEIGHT;
	}

	public Game() {
		this.setPreferredSize(new Dimension(Game.RENDERER_WIDTH, Game.RENDERER_HEIGHT));

		this.frame = new JFrame();

		this.frame.setTitle(StringGame.TITLE.getValue());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		this.renderer = new BufferedImage(Game.RENDERER_WIDTH, Game.RENDERER_HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.showFPS = true;
	}

	private void tick() {
		// Code
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, Game.RENDERER_WIDTH, Game.RENDERER_HEIGHT);

		if (this.showFPS) {
			render.setColor(Color.BLACK);
			render.fillRect(Game.RENDERER_WIDTH - 120, 10, 110, 30);

			render.setColor(Color.WHITE);
			render.setFont(GameUtil.getFontDefault());
			render.drawString(String.format("FPS: %d", this.fps), Game.RENDERER_WIDTH - 115, 32);

			render.setColor(Color.WHITE);
			render.drawRect(Game.RENDERER_WIDTH - 120, 10, 110, 30);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(new Color(50, 50, 100));
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.drawImage(this.renderer, Game.RENDERER_X, Game.RENDERER_Y, Game.RENDERER_WIDTH, Game.RENDERER_HEIGHT, null);

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

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR.getValue(), JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}
