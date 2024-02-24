package controlpixel.screen;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.ButtonSmall;
import controlpixel.scenarios.levels.Level01;
import controlpixel.scenarios.levels.Level02;
import controlpixel.scenarios.levels.Level03;
import controlpixel.scenarios.levels.Level04;
import controlpixel.scenarios.levels.Level05;
import controlpixel.scenarios.levels.LevelTest;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringLevel;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;

public class SelectLevel01 extends Screen {

	public SelectLevel01(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerWidth = (game.getGameWidth() - ButtonSmall.getWidthPressed()) / 2;

		this.addLevel01And02And03(game, centerWidth);
		this.addLevel04And05And06(game, centerWidth);

		super.buttonSmalls.add(new ButtonSmall(game, StringScreen.MENU.getValue(), centerWidth, game.getGameHeight() - ButtonSmall.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.MAIN_MENU)));

		super.buttons.add(new Button(game, StringScreen.NEXT.getValue(), game.getGameWidth() - Button.getWidthPressed() - 50, game.getGameHeight() - Button.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.SELECT_LEVEL_02)));
	}

	private void addLevel01And02And03(Game game, int centerWidth) {
		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_01.getValue(), centerWidth - 100 - ButtonSmall.getWidthPressed(), 120, () -> {
			game.initializeScenario(new Level01(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_02.getValue(), centerWidth, 120, () -> {
			game.initializeScenario(new Level02(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_03.getValue(), centerWidth + 100 + ButtonSmall.getWidthPressed(), 120, () -> {
			game.initializeScenario(new Level03(game));
			game.updateGameStatus(GameStatus.RUN);
		}));
	}

	private void addLevel04And05And06(Game game, int centerWidth) {
		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_04.getValue(), centerWidth - 100 - ButtonSmall.getWidthPressed(), 220, () -> {
			game.initializeScenario(new Level04(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_05.getValue(), centerWidth, 220, () -> {
			game.initializeScenario(new Level05(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_06.getValue(), centerWidth + 100 + ButtonSmall.getWidthPressed(), 220, () -> {
			game.initializeScenario(new LevelTest(game));
			game.updateGameStatus(GameStatus.RUN);
		}));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.SELECT_LEVEL_01;
	}

}
