package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;
import controlpixel.util.CustomColors;
import controlpixel.util.Util;

public class Level12 extends Scenario {

	public Level12(Game game) {
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
			{' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', 'L', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'R', ' '},
			{' ', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', ' '},
			{'W', 'F', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'H', 'W'}
		};
	}

	@Override
	protected void setStrings() {
		super.texts.add(new TextRender(StringLevel.LEVEL_12.getValue(), 25, 25, CustomColors.WHITE, super.game.getRender()));

		super.texts.add(new TextRender(StringLevel.LEVEL_VICTORY.getValue(), 25, 50, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LEVEL_BEAT_GAME.getValue(), 25, 75, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LEVEL_THANKS_FOR_PLAYING.getValue(), 25, 100, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.LEVEL_PRESS_P_ESC.getValue(), 25, 125, CustomColors.WHITE, super.game.getRender()));
	}

	@Override
	protected Scenario nextLevel() {
		Util.getSave().setLevel12(true);

		return new Level12(super.game);
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level12(super.game);
	}

}
