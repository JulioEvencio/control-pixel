package controlpixel.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.entities.CrystalJump;
import controlpixel.scenarios.entities.CrystalReverse;
import controlpixel.scenarios.entities.Entity;
import controlpixel.scenarios.entities.Water;
import controlpixel.scenarios.entities.player.Player;
import controlpixel.scenarios.entities.portal.Portal;
import controlpixel.scenarios.interfaces.InterfaceBlocksSelected;
import controlpixel.scenarios.tiles.Block;
import controlpixel.scenarios.tiles.GrassCenter;
import controlpixel.scenarios.tiles.GrassLeft;
import controlpixel.scenarios.tiles.GrassRight;
import controlpixel.scenarios.tiles.GroundCenter;
import controlpixel.scenarios.tiles.GroundLeft;
import controlpixel.scenarios.tiles.GroundRight;
import controlpixel.scenarios.tiles.Tile;
import controlpixel.util.Camera;
import controlpixel.util.CustomColors;
import controlpixel.util.GameStatus;
import controlpixel.util.Rect;

public abstract class Scenario {

	protected final Game game;

	private boolean levelFinished;

	protected char[][] map;

	protected final List<TextRender> texts;

	private Portal portal;

	private final List<Tile> tiles;
	private final List<Entity> entities;
	private final List<Entity> entitiesJump;
	private final List<Entity> entitiesEnemy;
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

	private final InterfaceBlocksSelected interfaceBlocksSelected;

	public Scenario(Game game) {
		this.game = game;

		this.levelFinished = false;

		this.texts = new ArrayList<>();

		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.entitiesJump = new ArrayList<>();
		this.entitiesEnemy = new ArrayList<>();
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

		this.interfaceBlocksSelected = new InterfaceBlocksSelected(game, this);

		this.buildGame();
	}

	public double getGravity() {
		return this.gravity;
	}

	public Portal getPortal() {
		return this.portal;
	}

	public List<Entity> getEntitiesJump() {
		return this.entitiesJump;
	}

	public List<Entity> getEntitiesEnemy() {
		return this.entitiesEnemy;
	}

	public List<Entity> getEntitiesReverse() {
		return this.entitiesReverse;
	}

	public int getTypeBuild() {
		return this.typeBuild;
	}

	protected abstract void initializeLevel();

	protected abstract Scenario restartScenario();

	private void buildGame() {
		this.initializeLevel();

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				switch (map[i][j]) {
					case ' ':
						this.entities.add(new Entity(50 * j, 50 * i, 50, 50));
						break;
					case 'G':
						this.tiles.add(new GroundCenter(50 * j, 50 * i));
						break;
					case 'C':
						this.tiles.add(new GrassCenter(50 * j, 50 * i));
						break;
					case 'R':
						this.tiles.add(new GrassRight(50 * j, 50 * i));
						break;
					case 'H':
						this.tiles.add(new GroundRight(50 * j, 50 * i));
						break;
					case 'F':
						this.tiles.add(new GroundLeft(50 * j, 50 * i));
						break;
					case 'L':
						this.tiles.add(new GrassLeft(50 * j, 50 * i));
						break;
					case 'W':
						this.entitiesEnemy.add(new Water(50 * j, 50 * i));
						break;
					case 'P':
						this.portal = new Portal(50 * j, 50 * i);
						break;
					case 'J':
						this.mouseMotionX = 50 * j + 5;
						this.mouseMotionY = 50 * i + 5;
						this.player.setPosition(50 * j + 15, 50 * i + 10);
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

	public void setLevelFinished() {
		this.levelFinished = true;
	}

	private void scenarioRestart() {
		this.game.initializeScenario(this.restartScenario());
	}

	private boolean canRender(Rect object) {
		Rect areaCamera = new Rect(Camera.x, Camera.y, this.game.getGameWidth(), this.game.getGameHeight());

		return areaCamera.isColliding(object);
	}

	private void addBlock(Entity entity) {
		this.tiles.add(new Block(entity.getRect().getX(), entity.getRect().getY()));
	}

	private void addBlockJump(Entity entity) {
		this.entitiesJump.add(new CrystalJump(entity.getRect().getX(), entity.getRect().getY()));
	}

	private void addBlockReverse(Entity entity) {
		this.entitiesReverse.add(new CrystalReverse(entity.getRect().getX(), entity.getRect().getY()));
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
		} else if (this.levelFinished) {
			System.out.println("Level Finished");
		} else {
			this.portal.tick();
			this.player.tick();

			if (this.player.isDead()) {
				this.scenarioRestart();
			}
		}
	}

	public void render(Graphics render) {
		render.setColor(CustomColors.BLUE);
		render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

		for (TextRender text : this.texts) {
			if (this.canRender(text.getRect())) {
				text.render(render);
			}
		}

		for (Entity entity : this.entitiesEnemy) {
			if (this.canRender(entity.getRect())) {
				entity.render(render);
			}
		}

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

		if (this.canRender(this.portal.getRect())) {
			this.portal.render(render);
		}

		this.player.render(render);

		if (showMouseMotionRect) {
			render.setColor(Color.WHITE);
			render.drawRect(this.mouseMotionRect.getX(), this.mouseMotionRect.getY(), this.mouseMotionRect.getWidth(), this.mouseMotionRect.getHeight());
		}

		this.interfaceBlocksSelected.renderBlocksSelected(render);
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
			this.scenarioRestart();
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

		if (e.getButton() == MouseEvent.BUTTON3) {
			this.buildMode = false;
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
