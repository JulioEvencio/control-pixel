package controlpixel.scenarios.levels;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;

public class LevelTest extends Scenario {

	public LevelTest(Game game) {
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
		super.texts.add(new TextRender("Level Test", 80, 80, Color.WHITE, super.game.getRender()));
	}
	
	@Override
	protected Scenario nextLevel() {
		return new LevelTest(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new LevelTest(super.game);
	}

}