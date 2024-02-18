package controlpixel.scenarios;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
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

	protected final Game game;

	protected char[][] map;

	protected final List<Tile> tiles;
	private final List<Entity> entities;
	private final List<Entity> entitiesJump;
	private final List<Entity> entitiesReverse;

	private final Player player;

	private final double gravity;

	private int mouseMotionX;
	private int mouseMotionY;

	private final Rect mouseMotionRect;

	private boolean hasClick;

	private int mouseClickX;
	private int mouseClickY;

	private boolean buildMode;

	private int typeBuild;

	public Scenario(Game game) {
		this.game = game;

		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.entitiesJump = new ArrayList<>();
		this.entitiesReverse = new ArrayList<>();

		this.player = new Player(this);

		this.gravity = 0.5;

		this.mouseMotionX = 0;
		this.mouseMotionY = 0;

		this.mouseMotionRect = new Rect(0, 0, 50, 50);

		this.hasClick = false;

		this.mouseClickX = 0;
		this.mouseClickY = 0;

		this.buildMode = true;

		this.typeBuild = TypeBuild.BLOCK;

		this.buildGame();
	}

	public double getGravity() {
		return this.gravity;
	}

	public List<Entity> getEntitiesJump() {
		return this.entitiesJump;
	}

	public List<Entity> getEntitiesReverse() {
		return this.entitiesReverse;
	}

	protected abstract void initializeLevel();

	protected abstract Scenario restartScenario();

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

	private void renderBlocksSelected(Graphics render) {
		render.setColor(CustomColors.GRAY_DARK);
		render.fillRect(175, this.game.getGameHeight() - 90, 260, 75);

		render.setColor(CustomColors.PURPLE);
		render.fillRect(200, this.game.getGameHeight() - 75, 50, 50);

		render.setColor(CustomColors.GREEN_DARK);
		render.fillRect(280, this.game.getGameHeight() - 75, 50, 50);

		render.setColor(CustomColors.WHITE);
		render.fillRect(360, this.game.getGameHeight() - 75, 50, 50);

		int x = 0;

		if (this.typeBuild == TypeBuild.BLOCK) {
			x = 200;
		} else if (this.typeBuild == TypeBuild.JUMP) {
			x = 280;
		} else if (this.typeBuild == TypeBuild.REVERSE) {
			x = 360;
		}

		Graphics2D g = (Graphics2D) render;
		g.setStroke(new BasicStroke(3.0f));

		render.setColor(CustomColors.BLACK);
		render.drawRect(x, this.game.getGameHeight() - 75, 50, 50);

		g.setStroke(new BasicStroke(1.0f));
	}

	private void addBlock(Entity entity) {
		this.tiles.add(new Block(entity.getRect().getX(), entity.getRect().getY()));
	}

	private void addBlockJump(Entity entity) {
		this.entitiesJump.add(new Entity(entity.getRect().getX(), entity.getRect().getY(), CustomColors.GREEN_DARK));
	}

	private void addBlockReverse(Entity entity) {
		this.entitiesReverse.add(new Entity(entity.getRect().getX(), entity.getRect().getY(), CustomColors.WHITE));
	}

	private void build() {
		if (this.hasClick) {
			List<Entity> removeEntities = new ArrayList<>();

			for (Entity entity : this.entities) {
				if (entity.getRect().wasClicked(this.mouseClickX, this.mouseClickY)) {
					removeEntities.add(entity);

					if (this.typeBuild == TypeBuild.BLOCK) {
						this.addBlock(entity);
					} else if (this.typeBuild == TypeBuild.JUMP) {
						this.addBlockJump(entity);
					} else if (this.typeBuild == TypeBuild.REVERSE) {
						this.addBlockReverse(entity);
					}

					break;
				}
			}

			this.entities.removeAll(removeEntities);
			this.hasClick = false;
		}
	}

	public void tick() {
		if (this.buildMode) {
			this.build();
		} else {
			this.player.tick();
		}
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

		for (Entity entity : this.entitiesJump) {
			if (this.canRender(entity.getRect())) {
				entity.render(render);
			}
		}

		for (Entity entity : this.entitiesReverse) {
			if (this.canRender(entity.getRect())) {
				entity.render(render);
			}
		}

		this.player.render(render);

		if (showMouseMotionRect) {
			render.setColor(Color.WHITE);
			render.drawRect(this.mouseMotionRect.getX(), this.mouseMotionRect.getY(), this.mouseMotionRect.getWidth(), this.mouseMotionRect.getHeight());
		}

		this.renderBlocksSelected(render);
	}

	public void keyPressed(KeyEvent e) {
		// Code
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.buildMode = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
			this.typeBuild = TypeBuild.BLOCK;
		} else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
			this.typeBuild = TypeBuild.JUMP;
		} else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
			this.typeBuild = TypeBuild.REVERSE;
		}

		if (e.getKeyCode() == KeyEvent.VK_R) {
			this.game.initializeScenario(this.restartScenario());
		}

		if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.game.updateGameStatus(GameStatus.PAUSE);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.hasClick = true;

			this.mouseClickX = e.getX();
			this.mouseClickY = e.getY();

			if (this.game.isFullscreen()) {
				this.mouseClickX -= this.game.getRendererX();
				this.mouseClickY -= this.game.getRendererY();

				this.mouseClickX *= (double) this.game.getGameWidth() / (double) this.game.getRendererWidth();
				this.mouseClickY *= (double) this.game.getGameHeight() / (double) this.game.getRendererHeight();
			}
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

	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotation = e.getWheelRotation();

		if (rotation > 0) {
            this.typeBuild++;

            if (this.typeBuild > TypeBuild.MAX) {
            	this.typeBuild = TypeBuild.MIN;
            }
        } else if (rotation < 0) {
            this.typeBuild--;

            if (this.typeBuild < TypeBuild.MIN) {
            	this.typeBuild = TypeBuild.MAX;
            }
        }
	}

}
