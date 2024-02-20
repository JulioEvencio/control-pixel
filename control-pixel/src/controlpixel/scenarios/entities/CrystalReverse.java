package controlpixel.scenarios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Spritesheet;

public class CrystalReverse extends Entity {

	public static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteMap(80, 128, 16, 16);
	}

	public CrystalReverse(int x, int y) {
		super(x + 20, y + 20, 10, 10);
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(CrystalReverse.sprite, super.rect.getX() - Camera.x - 6, super.rect.getY() - Camera.y - 8, 25, 25, null);
	}

}
