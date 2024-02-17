package controlpixel.scenarios.tiles;

import java.awt.Color;
import java.awt.Graphics;

import controlpixel.util.Camera;
import controlpixel.util.Rect;

public abstract class Tile {

	private final Rect rect;
	private final Color color;

	public Tile(Rect rect, Color color) {
		this.rect = rect;
		this.color = color;
	}

	public Rect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect(this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight());
	}

}
