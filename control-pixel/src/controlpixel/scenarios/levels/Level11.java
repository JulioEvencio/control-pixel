package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;
import controlpixel.util.CustomColors;
import controlpixel.util.Util;

public class Level11 extends Scenario {

	public Level11(Game game) {
		super(game);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', ' ', 'J', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'P', ' ', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', ' ', 'B', ' ', ' ', 'B', 'B', 'B', ' ', 'B', 'B', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', 'B', 'B', 'B', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', 'B', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', 'B', 'B', 'B', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', 'B', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', 'B', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', 'B', ' ', 'B', ' ', ' ', 'B', 'B', ' ', ' ', 'B', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', 'B', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', 'B', 'B', 'B', 'B', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', ' ', ' ', ' ', 'B', ' ', 'B', ' ', ' ', 'B', 'B', ' ', ' ', 'B', 'B', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', 'B', ' ', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', ' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' ', 'B', 'B', ' ', ' ', 'B', ' ', 'B', ' ', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', ' ', 'B', 'B', 'B', ' ', 'B', ' ', 'B', ' ', 'B', ' ', 'B', 'B', 'B', ' ', 'B', 'B', 'B', 'B', 'B', ' ', 'B', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' '},
			{' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', ' '},
			{'W', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'W'}
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_11.getValue(), 25, 25, CustomColors.WHITE, super.game.getRender()));
	}

	@Override
	protected Scenario nextLevel() {
		Util.getSave().setLevel12(true);

		return new Level12(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level11(super.game);
	}

}
