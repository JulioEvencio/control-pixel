package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;
import controlpixel.util.CustomColors;
import controlpixel.util.Util;

public class Level07 extends Scenario {

	public Level07(Game game) {
		super(game);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', 'B', 'B', 'B', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' ', 'B', ' '},
			{' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' ', 'B', 'B', 'B', 'B', ' '},
			{' ', 'B', 'P', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', 'B', 'B', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', 'B', 'B', ' ', ' ', ' ', ' ', 'B', ' ', 'B', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'L', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'R', ' '},
			{' ', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', ' '},
			{'W', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', 'W'}
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_07.getValue(), 25, 25, CustomColors.WHITE, super.game.getRender()));
	}

	@Override
	protected Scenario nextLevel() {
		Util.getSave().setLevel08(true);

		return new Level08(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level07(super.game);
	}

}
