package controlpixel.scenarios.entities.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controlpixel.util.Camera;
import controlpixel.util.Rect;
import controlpixel.util.Sprite;
import controlpixel.util.Spritesheet;

public class PlayerSprite extends Sprite {

	public PlayerSprite(int x, int y, int width, int height) {
		super(new Rect(x, y, width, height), 5, 4);
	}

	public void reverseDirection() {
		if (super.currentArraySprite == 0) {
			super.currentArraySprite = 1;
		} else if (super.currentArraySprite == 1) {
			super.currentArraySprite = 0;
		}
	}

	@Override
	protected void loadSpritesheet() {
		super.sprites = new BufferedImage[2][super.maxIndex];

		for (int i = 0; i < super.sprites[0].length; i++) {
			super.sprites[0][i] = Spritesheet.getSpritePlayer(696 + (i * 24), 0, 24, 24);
		}

		for (int i = 0; i < super.sprites[1].length; i++) {
			super.sprites[1][i] = Spritesheet.getSpritePlayer(624 - (i * 24), 0, 24, 24);
		}

		super.currentArraySprite = 0;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(super.sprites[super.currentArraySprite][super.index], super.rect.getX() - Camera.x, super.rect.getY() - Camera.y, super.rect.getWidth(), super.rect.getHeight(), null);
	}

}
