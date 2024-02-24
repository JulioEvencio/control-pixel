package controlpixel.screen;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.ButtonSmall;
import controlpixel.scenarios.levels.Level07;
import controlpixel.scenarios.levels.Level08;
import controlpixel.scenarios.levels.LevelTest;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringLevel;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;

public class SelectLevel02 extends Screen {

	public SelectLevel02(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerWidth = (game.getGameWidth() - ButtonSmall.getWidthPressed()) / 2;

		this.addLevel07And08And09(game, centerWidth);
		this.addLevel10And11And12(game, centerWidth);

		super.buttons.add(new Button(game, StringScreen.BACK.getValue(), 50, game.getGameHeight() - Button.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.SELECT_LEVEL_01)));

		super.buttonSmalls.add(new ButtonSmall(game, StringScreen.MENU.getValue(), centerWidth, game.getGameHeight() - ButtonSmall.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.MAIN_MENU)));
	}

	private void addLevel07And08And09(Game game, int centerWidth) {
		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_07.getValue(), centerWidth - 100 - ButtonSmall.getWidthPressed(), 120, () -> {
			game.initializeScenario(new Level07(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_08.getValue(), centerWidth, 120, () -> {
			game.initializeScenario(new Level08(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_09.getValue(), centerWidth + 100 + ButtonSmall.getWidthPressed(), 120, () -> {
			game.initializeScenario(new LevelTest(game));
			game.updateGameStatus(GameStatus.RUN);
		}));
	}

	private void addLevel10And11And12(Game game, int centerWidth) {
		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_10.getValue(), centerWidth - 100 - ButtonSmall.getWidthPressed(), 220, () -> {
			game.initializeScenario(new LevelTest(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_11.getValue(), centerWidth, 220, () -> {
			game.initializeScenario(new LevelTest(game));
			game.updateGameStatus(GameStatus.RUN);
		}));

		super.buttonSmalls.add(new ButtonSmall(game, StringLevel.LEVEL_12.getValue(), centerWidth + 100 + ButtonSmall.getWidthPressed(), 220, () -> {
			game.initializeScenario(new LevelTest(game));
			game.updateGameStatus(GameStatus.RUN);
		}));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.SELECT_LEVEL_02;
	}

}
