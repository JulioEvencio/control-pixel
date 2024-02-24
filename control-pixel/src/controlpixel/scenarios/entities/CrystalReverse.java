package controlpixel.scenarios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Spritesheet;

public class CrystalReverse extends Entity {

	public static final BufferedImage sprite;

	private boolean enabled;

	static {
		sprite = Spritesheet.getSpriteMap(80, 128, 16, 16);
	}

	public CrystalReverse(int x, int y) {
		super(x + 19, y + 17, 15, 15);

		this.enabled = true;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setDisabled() {
		this.enabled = false;
	}

	@Override
	public void render(Graphics render) {
		render.drawImage(CrystalReverse.sprite, super.rect.getX() - Camera.x - 5, super.rect.getY() - Camera.y - 5, 25, 25, null);
	}

}
