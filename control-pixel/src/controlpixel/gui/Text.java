package controlpixel.gui;

import java.awt.Color;
import java.awt.Graphics;

import controlpixel.GameUtil;

public class Text {

	private final String text;

	private final int x;
	private final int y;

	private final Color color;

	public Text(String text, int x, int y, Color color) {
		this.text = text;

		this.x = x;
		this.y = y;

		this.color = color;
	}

	public String getText() {
		return this.text;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.setFont(GameUtil.getFontDefault());
		render.drawString(this.text, this.x, this.y);
	}

}
