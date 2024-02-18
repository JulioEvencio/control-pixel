package controlpixel.scenarios.interfaces;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controlpixel.Game;
import controlpixel.scenarios.Scenario;
import controlpixel.scenarios.TypeBuild;
import controlpixel.scenarios.entities.CrystalJump;
import controlpixel.scenarios.entities.CrystalReverse;
import controlpixel.scenarios.tiles.Block;
import controlpixel.util.CustomColors;
import controlpixel.util.Spritesheet;

public class InterfaceBlocksSelected {
	
	private final Game game;
	private final Scenario scenario;

	private static final BufferedImage sprite;

	static {
		sprite = Spritesheet.getSpriteGUI(145, 65, 46, 14);
	}

	public InterfaceBlocksSelected(Game game, Scenario scenario) {
		this.game = game;
		this.scenario = scenario;
	}

	public void renderBlocksSelected(Graphics render) {
		int positionBlocksSelected = (this.game.getGameWidth() - 260) / 2;

		render.drawImage(InterfaceBlocksSelected.sprite, positionBlocksSelected, this.game.getGameHeight() - 90, 250, 75, null);

		render.drawImage(Block.sprite, positionBlocksSelected + 25, this.game.getGameHeight() - 75, 50, 50, null);
		render.drawImage(CrystalJump.sprite, positionBlocksSelected + 100, this.game.getGameHeight() - 75, 50, 50, null);
		render.drawImage(CrystalReverse.sprite, positionBlocksSelected + 175, this.game.getGameHeight() - 75, 50, 50, null);

		int x = 0;

		if (this.scenario.getTypeBuild() == TypeBuild.BLOCK) {
			x = positionBlocksSelected + 25;
		} else if (this.scenario.getTypeBuild() == TypeBuild.JUMP) {
			x = positionBlocksSelected + 100;
		} else if (this.scenario.getTypeBuild() == TypeBuild.REVERSE) {
			x = positionBlocksSelected + 175;
		}

		Graphics2D g = (Graphics2D) render;
		g.setStroke(new BasicStroke(3.0f));

		render.setColor(CustomColors.BLACK);
		render.drawRect(x, this.game.getGameHeight() - 75, 50, 50);

		g.setStroke(new BasicStroke(1.0f));
	}

}
