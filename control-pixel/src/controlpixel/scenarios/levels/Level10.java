package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;
import controlpixel.util.CustomColors;
import controlpixel.util.Util;

public class Level10 extends Scenario {

	public Level10(Game game) {
		super(game);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' ', ' '},
			{' ', ' ', 'B', 'J', 'B', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' ', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', 'B', 'B', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', 'B', 'B', 'B', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', 'B', 'B', 'B', 'B', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', 'B', 'B', ' ', ' ', 'B', ' ', 'B', 'B', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', ' ', 'B', ' ', ' ', 'B', 'B', 'B', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', 'B', 'B', 'B', 'B', 'B', 'B', ' ', 'B', ' ', 'B', ' ', ' '},
			{' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', 'B', ' ', ' '},
			{' ', 'L', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'R', ' '},
			{' ', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', ' '},
			{'W', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', 'W'}
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_10.getValue(), 25, 25, CustomColors.WHITE, super.game.getRender()));
	}

	@Override
	protected Scenario nextLevel() {
		Util.getSave().setLevel11(true);

		return new Level11(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level10(super.game);
	}

}
