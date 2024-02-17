package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.scenarios.levels.Level01;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class MainMenu extends Screen {

	public MainMenu(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(game, StringScreen.NEW_GAME.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 120, () -> {
			game.initializeScenario(new Level01(game));
			game.updateGameStatus(GameStatus.RUN);
		}));
		super.buttons.add(new Button(game, StringScreen.CREDITS.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 220, () -> game.updateGameStatus(GameStatus.CREDITS)));
		super.buttons.add(new Button(game, StringScreen.EXIT.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 320, () -> game.updateGameStatus(GameStatus.EXIT)));

		super.texts.add(new Text(StringScreen.TEXT_FULL_SCREEN.getValue(), (game.getGameWidth() - Util.getWidthTextFullScreen()) / 2, 420, Color.WHITE));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.MAIN_MENU;
	}

}
