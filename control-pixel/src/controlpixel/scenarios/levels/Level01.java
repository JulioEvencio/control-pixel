package controlpixel.scenarios.levels;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;

public class Level01 extends Scenario {

	public Level01(Game game) {
		super(game);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
			{' ', 'L', 'C', 'R', ' ', 'L', 'C', 'C', 'C', 'C', 'C', 'R', ' ', 'L', 'C', 'C'},
			{' ', 'F', 'G', 'H', ' ', 'F', 'G', 'G', 'G', 'G', 'G', 'H', ' ', 'F', 'G', 'G'},
			{'W', 'F', 'G', 'H', 'W', 'F', 'G', 'G', 'G', 'G', 'G', 'H', 'W', 'F', 'G', 'G'},
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_01.getValue(), 80, 80, Color.WHITE, super.game.getRender()));
	}

	protected Scenario restartScenario() {
		return new Level01(super.game);
	}

}
