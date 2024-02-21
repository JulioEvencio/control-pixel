package controlpixel.scenarios.entities.portal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Rect;
import controlpixel.util.Sprite;
import controlpixel.util.Spritesheet;

public class PortalSprite extends Sprite {

	public PortalSprite(int x, int y, int width, int height) {
		super(new Rect(x, y, width, height), 5, 5);
	}

	@Override
	protected void loadSpritesheet() {
		super.sprites = new BufferedImage[1][super.maxIndex];

		for (int i = 0; i < super.sprites[0].length; i++) {
			super.sprites[0][i] = Spritesheet.getSpritePortal((i * 32), 0, 32, 32);
		}

		super.currentArraySprite = 0;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(super.sprites[super.currentArraySprite][super.index], super.rect.getX() - Camera.x, super.rect.getY() - Camera.y, super.rect.getWidth(), super.rect.getHeight(), null);
	}

}
