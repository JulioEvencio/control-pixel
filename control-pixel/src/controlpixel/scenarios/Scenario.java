package controlpixel.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import controlpixel.Game;
import controlpixel.scenarios.entities.Entity;
import controlpixel.scenarios.entities.Player;
import controlpixel.scenarios.tiles.Block;
import controlpixel.scenarios.tiles.Tile;
import controlpixel.util.Camera;
import controlpixel.util.CustomColors;
import controlpixel.util.GameStatus;
import controlpixel.util.Rect;

public abstract class Scenario {

	private final Game game;

	protected char[][] map;

	protected final List<Tile> tiles;
	private final List<Entity> entities;

	private final Player player;

	private final double gravity;

	private int mouseMotionX;
	private int mouseMotionY;

	private final Rect mouseMotionRect;

	public Scenario(Game game) {
		this.game = game;

		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();

		this.player = new Player(this);

		this.gravity = 0.5;

		this.mouseMotionX = 0;
		this.mouseMotionY = 0;

		this.mouseMotionRect = new Rect(0, 0, 50, 50);

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
					case ' ':
						this.entities.add(new Entity(50 * j, 50 * i, CustomColors.INVISIBLE));
						break;
					case 'B':
						this.tiles.add(new Block(50 * j, 50 * i));
						break;
					case 'J':
						this.player.setPosition(50 * j, 50 * i);
						this.entities.add(new Entity(50 * j, 50 * i, CustomColors.INVISIBLE));
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

		boolean showMouseMotionRect = false;

		for (Entity entity : this.entities) {
			if (this.canRender(entity.getRect())) {
				entity.render(render);

				if (entity.getRect().wasClicked(this.mouseMotionX, this.mouseMotionY)) {
					showMouseMotionRect = true;

					this.mouseMotionRect.setX(entity.getRect().getX());
					this.mouseMotionRect.setY(entity.getRect().getY());
				}
			}
		}

		this.player.render(render);

		if (showMouseMotionRect) {
			render.setColor(Color.WHITE);
			render.drawRect(this.mouseMotionRect.getX(), this.mouseMotionRect.getY(), this.mouseMotionRect.getWidth(), this.mouseMotionRect.getHeight());
		}
	}

	public void keyPressed(KeyEvent e) {
		// Code
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.game.updateGameStatus(GameStatus.PAUSE);
		}
	}

	public void mouseMoved(MouseEvent e) {
		this.mouseMotionX = e.getX();
		this.mouseMotionY = e.getY();

		if (this.game.isFullscreen()) {
			this.mouseMotionX -= this.game.getRendererX();
			this.mouseMotionY -= this.game.getRendererY();

			this.mouseMotionX *= (double) this.game.getGameWidth() / (double) this.game.getRendererWidth();
			this.mouseMotionY *= (double) this.game.getGameHeight() / (double) this.game.getRendererHeight();
		}
	}

}
