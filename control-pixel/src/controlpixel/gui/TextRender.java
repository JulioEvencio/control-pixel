package controlpixel.gui;

import java.awt.Color;
import java.awt.Graphics;

import controlpixel.util.Camera;
import controlpixel.util.Rect;
import controlpixel.util.Util;

public class TextRender {

	private final String text;

	private final Rect rect;

	private final Color color;

	public TextRender(String text, int x, int y, Color color, Graphics render) {
		this.text = text;

		int width = render.getFontMetrics().stringWidth(text);
		int height = render.getFontMetrics().getHeight();

		this.rect = new Rect(x, y, width, height);

		this.color = color;
	}

	public String getText() {
		return this.text;
	}

	public Rect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.setFont(Util.getFontDefault());
		render.drawString(this.text, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y);
	}

}
