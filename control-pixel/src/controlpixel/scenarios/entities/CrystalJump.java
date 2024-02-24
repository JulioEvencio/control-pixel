package controlpixel.scenarios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Spritesheet;

public class CrystalJump extends Entity {

	public static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteMap(96, 128, 16, 16);
	}

	public CrystalJump(int x, int y) {
		super(x + 19, y + 17, 15, 15);
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(CrystalJump.sprite, super.rect.getX() - Camera.x - 5, super.rect.getY() - Camera.y - 5, 25, 25, null);
	}

}
