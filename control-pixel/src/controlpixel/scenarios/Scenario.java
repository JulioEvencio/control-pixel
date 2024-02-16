package controlpixel.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import controlpixel.Game;
import controlpixel.scenarios.entities.Player;
import controlpixel.scenarios.tiles.Block;
import controlpixel.scenarios.tiles.Tile;
import controlpixel.util.Camera;
import controlpixel.util.CustomColors;
import controlpixel.util.Rect;

public abstract class Scenario {

	private final Game game;

	protected char[][] map;

	protected final List<Tile> tiles;

	private final Player player;

	private final double gravity;

	public Scenario(Game game) {
		this.game = game;

		this.tiles = new ArrayList<>();

		this.player = new Player(this);

		this.gravity = 0.5;

		this.buildGame();
	}

	public double getGravity() {
		return this.gravity;
	}

	protected abstract void initializeLevel();

	private void buildGame() {
		this.initializeLevel();

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				switch (map[i][j]) {
					case 'B':
						this.tiles.add(new Block(50 * j, 50 * i));
						break;
					case 'J':
						this.player.setPosition(50 * j, 50 * i);
						break;
				}
			}
		}
	}

	public boolean isFree(Rect rect) {
		for (Tile tile : this.tiles) {
			if (tile.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	private boolean canRender(Rect object) {
		Rect areaCamera = new Rect(Camera.x, Camera.y, this.game.getGameWidth(), this.game.getGameHeight());

		return areaCamera.isColliding(object);
	}

	public void tick() {
		this.player.tick();
	}

	public void render(Graphics render) {
		render.setColor(CustomColors.PURPLE_DARK);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (Tile tile : this.tiles) {
			if (this.canRender(tile.getRect())) {
				tile.render(render);
			}
		}

		this.player.render(render);
	}

	public void keyPressed(KeyEvent e) {
		// Code
	}

	public void keyReleased(KeyEvent e) {
		// Code
	}

}
