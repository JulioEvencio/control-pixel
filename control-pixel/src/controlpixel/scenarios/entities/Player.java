package controlpixel.scenarios.entities;

import java.awt.Color;
import java.awt.Graphics;

import controlpixel.scenarios.Scenario;
import controlpixel.util.Camera;
import controlpixel.util.CustomColors;
import controlpixel.util.Rect;

public class Player {

	private final Rect rect;

	private int direction;

	private double speedX;
	private double speedY;

	private boolean isJump;
	private final int jumpHeight;
	private int jumpFrames;

	private final Color color;

	private final Scenario scenario;

	public Player(Scenario scenario) {
		this.rect = new Rect(0, 0, 50, 50);

		this.direction = 1;

		this.speedX = 3.0;
		this.speedY = 0.0;

		this.isJump = false;
		this.jumpHeight = 75;
		this.jumpFrames = 0;

		this.color = CustomColors.GRAY;

		this.scenario = scenario;
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}

	private void reverseDirection() {
		this.direction *= -1;
	}

	public void applyGravity() {
		this.speedY += this.scenario.getGravity();

		if (this.speedY > 8) {
			this.speedY = 8;
		}

		for (int i = 0; i < this.speedY; i++) {
			if (this.scenario.isFree(new Rect(this.rect.getX(), this.rect.getY() + 1, this.rect.getWidth(), this.rect.getHeight()))) {
				this.rect.setY(this.rect.getY() + 1);
			} else {
				this.speedY = 0;
				break;
			}
		}
	}

	private void toMove() {
		for (int i = 0; i < this.speedX; i++) {
			if (this.scenario.isFree(new Rect(this.rect.getX() + this.direction, this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()))) {
				this.rect.setX(this.rect.getX() + this.direction);

				if (this.scenario.isFree(new Rect(this.rect.getX(), this.rect.getY() + 1, this.rect.getWidth(), this.rect.getHeight()))) {
					this.rect.setY(this.rect.getY() + 1);
				}
			}
		}
	}

	public void toJump() {
		if (this.jumpFrames < 9) {
			this.speedY = 8;
		} else if (this.jumpFrames < 18) {
			this.speedY = 7;
		} else if (this.jumpFrames < 27) {
			this.speedY = 6;
		} else if (this.jumpFrames < 36) {
			this.speedY = 5;
		} else if (this.jumpFrames < 45) {
			this.speedY = 4;
		} else {
			this.speedY = 3;
		}

		for (int i = 0; i < this.speedY; i++) {
			if (this.jumpFrames < this.jumpHeight && this.scenario.isFree(new Rect(this.rect.getX(), this.rect.getY() - 1, this.rect.getWidth(), this.rect.getHeight()))) {
				this.rect.setY(this.rect.getY() - 1);
				this.jumpFrames++;
			} else {
				this.speedY = 0;
				this.jumpFrames = 0;
				this.isJump = false;
			}
		}
	}

	public void tick() {
		if (this.isJump) {
			this.toJump();
		} else {
			this.applyGravity();
		}

		this.toMove();

		if (!this.isJump && !this.scenario.isFree(new Rect(this.rect.getX(), this.rect.getY() + 1, this.rect.getWidth(), this.rect.getHeight()))) {
			for (Entity entity : this.scenario.getEntitiesJump()) {
				if (this.rect.isColliding(entity.getRect())) {
					this.isJump = true;

					break;
				}
			}
		}

		for (Entity entity : this.scenario.getEntitiesReverse()) {
			if (this.rect.isColliding(entity.getRect())) {
				this.reverseDirection();

				break;
			}
		}
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect((int) (this.rect.getX() - Camera.x), (int) (this.rect.getY() - Camera.y), this.rect.getWidth(), this.rect.getHeight());
	}

}
