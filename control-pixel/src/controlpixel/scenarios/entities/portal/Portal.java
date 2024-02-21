package controlpixel.scenarios.entities.portal;

import java.awt.Graphics;

import controlpixel.scenarios.entities.Entity;

public class Portal extends Entity {

	private final PortalSprite sprite;

	public Portal(int x, int y) {
		super(x + 18, y + 25, 15, 15);

		this.sprite = new PortalSprite(x, y, 50, 50);
	}

	public void tick() {
		this.sprite.tick();
	}

	@Override
	public void render(Graphics render) {
		this.sprite.render(render);
	}

}
