package controlpixel.scenarios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Spritesheet;

public class Water extends Entity {

	private static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteMap(80, 80, 16, 16);
	}

	public Water(int x, int y) {
		super(x, y, 50, 50);
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(Water.sprite, super.rect.getX() - Camera.x, super.rect.getY() - Camera.y, super.rect.getWidth(), super.rect.getHeight(), null);
	}

}
