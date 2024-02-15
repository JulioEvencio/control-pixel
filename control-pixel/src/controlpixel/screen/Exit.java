package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Util;

public class Exit extends Screen {

	public Exit(Game game) {
		super(game, StringGame.TITLE.getValue());
		
		super.texts.add(new Text(StringScreen.EXIT_GAME.getValue(), (super.game.getGameWidth() - Util.getWidthExitGame()) / 2, 180, Color.WHITE));

		super.buttons.add(new Button(super.game, StringScreen.YES.getValue(), super.game.getGameWidth() / 2 - 25 - Button.getWidthPressed(), 220, () -> Game.exitGame()));
		super.buttons.add(new Button(super.game, StringScreen.NO.getValue(), super.game.getGameWidth() / 2 + 25, 220, () -> game.updateGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.EXIT;
	}

}
