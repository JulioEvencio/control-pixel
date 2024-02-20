package controlpixel.scenarios.entities;

import java.awt.Graphics;

import controlpixel.util.Rect;

public class Entity {

	protected final Rect rect;

	public Entity(int x, int y, int width, int height) {
		this.rect = new Rect(x, y, width, height);
	}

	public Rect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		// Code
	}

}
