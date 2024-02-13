package controlpixel.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.Game;
import controlpixel.GameUtil;
import controlpixel.gui.event.EventOnClick;
import controlpixel.util.Spritesheet;

public class Button {

	private final Game game;

	private final String text;

	private final int x;
	private final int y;

	private static final int widthPressed;
	private static final int heightPressed;

	private static final int widthReleased;
	private static final int heightReleased;

	private boolean buttonIsPressed;

	private static final BufferedImage spriteButtonPressed;
	private static final BufferedImage spriteButtonReleased;

	private final EventOnClick eventOnClick;

	static {
		widthPressed = 46 * 5;
		heightPressed = 13 * 5;

		widthReleased = 46 * 5;
		heightReleased = 14 * 5;

		spriteButtonPressed = Spritesheet.getSprite(145, 98, 46, 13);
		spriteButtonReleased = Spritesheet.getSprite(145, 81, 46, 14);
	}

	public Button(Game game, String text, int x, int y, EventOnClick eventOnClick) {
		this.game = game;

		this.text = text;

		this.x = x;
		this.y = y;

		this.buttonIsPressed = false;

		this.eventOnClick = eventOnClick;
	}

	public String getText() {
		return this.text;
	}

	public static int getWidthPressed() {
		return Button.widthPressed;
	}

	public static int getHeightpressed() {
		return Button.heightPressed;
	}

	public void onClick() {
		this.eventOnClick.onClick();
	}

	public boolean wasClicked(int x, int y) {
		if (game.isFullscreen()) {
			x -= game.getRendererX();
			y -= game.getRendererY();

			x *= (double) game.getWidth() / (double) game.getRendererWidth();
			y *= (double) game.getHeight() / (double) game.getRendererHeight();
		}

		return x >= this.x && x <= this.x + Button.widthPressed && y >= this.y && y <= this.y + Button.heightPressed;
	}

	public void setButtonPressed() {
		this.buttonIsPressed = true;
	}

	public void setButtonReleased() {
		this.buttonIsPressed = false;
	}

	public void render(Graphics render) {
		if (this.buttonIsPressed) {
			render.drawImage(Button.spriteButtonPressed, this.x, this.y, Button.widthPressed, Button.heightPressed, null);
		} else {
			render.drawImage(Button.spriteButtonReleased, this.x, this.y, Button.widthReleased, Button.heightReleased, null);
		}

		render.setColor(Color.WHITE);
		render.setFont(GameUtil.getFontDefault());

		int textWidth = render.getFontMetrics().stringWidth(this.text);
		int textHeight = render.getFontMetrics().getHeight();

		int textX = this.x + (Button.widthPressed - textWidth) / 2;
		int textY = this.y + (Button.heightPressed - textHeight) / 2 + render.getFontMetrics().getAscent();

		render.drawString(this.text, textX, textY);
	}

}
