package controlpixel.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.Game;
import controlpixel.gui.event.EventOnClick;
import controlpixel.util.Spritesheet;
import controlpixel.util.Util;

public class ButtonSmall {

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
		widthPressed = 30 * 5;
		heightPressed = 13 * 5;

		widthReleased = 30 * 5;
		heightReleased = 14 * 5;

		spriteButtonPressed = Spritesheet.getSpriteGUI(113, 98, 30, 13);
		spriteButtonReleased = Spritesheet.getSpriteGUI(113, 81, 30, 14);
	}

	public ButtonSmall(Game game, String text, int x, int y, EventOnClick eventOnClick) {
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
		return ButtonSmall.widthPressed;
	}

	public static int getHeightpressed() {
		return ButtonSmall.heightPressed;
	}

	public void onClick() {
		this.eventOnClick.onClick();
	}

	public boolean wasClicked(int x, int y) {
		if (this.game.isFullscreen()) {
			x -= this.game.getRendererX();
			y -= this.game.getRendererY();

			x *= (double) this.game.getGameWidth() / (double) this.game.getRendererWidth();
			y *= (double) this.game.getGameHeight() / (double) this.game.getRendererHeight();
		}

		return x >= this.x && x <= this.x + ButtonSmall.widthPressed && y >= this.y && y <= this.y + ButtonSmall.heightPressed;
	}

	public void setButtonPressed() {
		this.buttonIsPressed = true;
	}

	public void setButtonReleased() {
		this.buttonIsPressed = false;
	}

	public void render(Graphics render) {
		if (this.buttonIsPressed) {
			render.drawImage(ButtonSmall.spriteButtonPressed, this.x, this.y, ButtonSmall.widthPressed, ButtonSmall.heightPressed, null);
		} else {
			render.drawImage(ButtonSmall.spriteButtonReleased, this.x, this.y, ButtonSmall.widthReleased, ButtonSmall.heightReleased, null);
		}

		render.setColor(Color.WHITE);
		render.setFont(Util.getFontDefault());

		int textWidth = render.getFontMetrics().stringWidth(this.text);
		int textHeight = render.getFontMetrics().getHeight();

		int textX = this.x + (ButtonSmall.widthPressed - textWidth) / 2;
		int textY = this.y + (ButtonSmall.heightPressed - textHeight) / 2 + render.getFontMetrics().getAscent();

		render.drawString(this.text, textX, textY);
	}

}
