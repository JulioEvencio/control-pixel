package controlpixel.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite {

	protected final Rect rect;

	protected int frames;
	protected final int maxFrames;

	protected int index;
	protected final int maxIndex;

	protected BufferedImage[][] sprites;

	protected int currentArraySprite;

	public Sprite(Rect rect, int maxFrames, int maxIndex) {
		this.rect = rect;

		this.frames = 0;
		this.maxFrames = maxFrames;

		this.index = 0;
		this.maxIndex = maxIndex;

		this.currentArraySprite = 0;

		this.loadSpritesheet();
	}

	protected abstract void loadSpritesheet();

	public void updatePosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}

	protected void updateFramesSprites() {
		frames++;

		if (frames >= maxFrames) {
			frames = 0;
			index++;

			if (index >= maxIndex) {
				index = 0;
			}
		}
	}

	public void tick() {
		this.updateFramesSprites();
	}

	public abstract void render(Graphics graphics);

}
