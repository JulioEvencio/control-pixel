package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.ButtonSmall;
import controlpixel.gui.Text;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class Exit extends Screen {

	public Exit(Game game) {
		super(game, StringGame.TITLE.getValue());
		
		super.texts.add(new Text(StringScreen.EXIT_GAME.getValue(), (game.getGameWidth() - Util.getWidthExitGame()) / 2, 180, Color.WHITE));

		super.buttonSmalls.add(new ButtonSmall(game, StringScreen.YES.getValue(), game.getGameWidth() / 2 - 25 - ButtonSmall.getWidthPressed(), 220, () -> Game.exitGame()));
		super.buttonSmalls.add(new ButtonSmall(game, StringScreen.NO.getValue(), game.getGameWidth() / 2 + 25, 220, () -> game.updateGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.EXIT;
	}

}
