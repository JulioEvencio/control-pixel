package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.gui.TextRender;
import controlpixel.scenarios.Scenario;
import controlpixel.strings.StringLevel;
import controlpixel.util.CustomColors;

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
		super.texts.add(new TextRender(StringLevel.LEVEL_01.getValue(), 25, 25, CustomColors.WHITE, super.game.getRender()));

		super.texts.add(new TextRender(StringLevel.TUTORIAL_HELP_PIXEL.getValue(), 25, 50, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_USE_MOUSE_BLOCK.getValue(), 25, 75, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_EXIT_BUILD_MODE.getValue(), 25, 100, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_RESTART.getValue(), 25, 125, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_PAUSE.getValue(), 25, 150, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_FULL_SCREEN.getValue(), 25, 175, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_FPS.getValue(), 25, 200, CustomColors.WHITE, super.game.getRender()));
		super.texts.add(new TextRender(StringLevel.TUTORIAL_MUSIC.getValue(), 25, 225, CustomColors.WHITE, super.game.getRender()));
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
