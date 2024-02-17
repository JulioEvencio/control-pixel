package controlpixel.util;

public class Rect {

	private int x;
	private int y;

	private final int width;
	private final int height;

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isColliding(Rect rect) {
		boolean collisionX = (this.x < rect.x + rect.width) && (this.x + this.width > rect.x);
		boolean collisionY = (this.y < rect.y + rect.height) && (this.y + this.height > rect.y);

		return collisionX && collisionY;
	}

	public boolean wasClicked(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}

}
