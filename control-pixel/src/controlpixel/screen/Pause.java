package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class Pause extends Screen {

	public Pause(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(game, StringScreen.CONTINUE.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 120, () -> game.updateGameStatus(GameStatus.RUN)));
		super.buttons.add(new Button(game, StringScreen.MENU.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 220, () -> game.updateGameStatus(GameStatus.MAIN_MENU)));
		super.buttons.add(new Button(game, StringScreen.EXIT.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 320, () -> game.updateGameStatus(GameStatus.EXIT)));

		super.texts.add(new Text(StringScreen.TEXT_FULL_SCREEN.getValue(), (game.getGameWidth() - Util.getWidthTextFullScreen()) / 2, 420, Color.WHITE));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.PAUSE;
	}

}
