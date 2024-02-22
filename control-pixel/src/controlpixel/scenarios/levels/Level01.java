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
			{' ', 'L', 'C', 'R', ' ', 'L', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C'},
			{' ', 'F', 'G', 'H', ' ', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
			{'W', 'F', 'G', 'H', 'W', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_01.getValue(), 25, 25, Color.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LINE_02.getValue(), 25, 50, Color.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LINE_03.getValue(), 25, 75, Color.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LINE_04.getValue(), 25, 100, Color.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LINE_05.getValue(), 25, 125, Color.WHITE, super.game.getRender()));
	}

	@Override
	protected Scenario nextLevel() {
		return new LevelTest(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level01(super.game);
	}

}
