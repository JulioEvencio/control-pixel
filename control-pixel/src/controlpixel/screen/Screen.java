package controlpixel.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.util.GameStatus;
import controlpixel.util.Spritesheet;
import controlpixel.util.Util;

public abstract class Screen {

	protected final Game game;

	private static final BufferedImage background;

	private final String title;

	protected final List<Text> texts;
	protected final List<Button> buttons;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int mouseX;
	private int mouseY;

	static {
		background = Spritesheet.getSprite(97, 37, 24, 22);
	}

	public Screen(Game game, String title) {
		this.game = game;

		this.title = title;

		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();

		this.mousePressed = false;
		this.mouseReleased = false;

		this.mouseX = 0;
		this.mouseY = 0;

		this.texts.add(new Text(String.format("Version: %s", this.game.getVERSION()), 50, 50, Color.WHITE));
	}

	public abstract GameStatus getGameStatus();

	public void tick() {
		if (this.mousePressed) {
			for (Button button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.setButtonPressed();
				}
			}

			this.mousePressed = false;
		}

		if (this.mouseReleased) {
			for (Button button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.onClick();
				}

				button.setButtonReleased();
			}

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(Screen.background, 0, 0, this.game.getGameWidth(), this.game.getGameHeight(), null);

		render.setColor(Color.WHITE);
		render.setFont(Util.getFontTitle());

		int titleWidth = render.getFontMetrics().stringWidth(this.title);

		render.drawString(this.title, (game.getGameWidth() - titleWidth) / 2, 80);

		for (Text text : this.texts) {
			text.render(render);
		}

		for (Button button : this.buttons) {
			button.render(render);
		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mousePressed = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mouseReleased = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

}
