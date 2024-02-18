package controlpixel.scenarios.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Rect;
import controlpixel.util.Spritesheet;

public class GrassCenter extends Tile {

	private final Rect rect;

	private static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteMap(16, 0, 16, 16);
	}

	public GrassCenter(int x, int y) {
		this.rect = new Rect(x, y, 50, 50);
	}

	@Override
	public Rect getRect() {
		return this.rect;
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(GrassCenter.sprite, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
