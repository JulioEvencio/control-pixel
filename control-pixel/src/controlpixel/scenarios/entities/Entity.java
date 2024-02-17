package controlpixel.scenarios.entities;

import java.awt.Color;
import java.awt.Graphics;

import controlpixel.util.Camera;
import controlpixel.util.Rect;

public class Entity {

	private final Rect rect;
	private final Color color;

	public Entity(int x, int y, Color color) {
		this.rect = new Rect(x, y, 50, 50);
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
