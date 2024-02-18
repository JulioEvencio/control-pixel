package controlpixel.scenarios.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Rect;
import controlpixel.util.Spritesheet;

public class GroundRight extends Tile {

	private final Rect rect;

	private static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteMap(32, 16, 16, 16);
	}

	public GroundRight(int x, int y) {
		this.rect = new Rect(x, y, 50, 50);
	}

	@Override
	public Rect getRect() {
		return this.rect;
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(GroundRight.sprite, this.rect.getX() - Camera.x, this.rect.getY() - Camera.y, this.rect.getWidth(), this.rect.getHeight(), null);
	}

}
