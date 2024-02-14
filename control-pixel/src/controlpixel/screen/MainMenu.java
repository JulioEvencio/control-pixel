package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;

public class MainMenu extends Screen {

	public MainMenu(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(super.game, StringScreen.NEW_GAME.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 120, () -> game.updateGameStatus(GameStatus.RUN)));
		super.buttons.add(new Button(super.game, StringScreen.CREDITS.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 220, () -> game.updateGameStatus(GameStatus.CREDITS)));
		super.buttons.add(new Button(super.game, StringScreen.EXIT.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 320, () -> game.updateGameStatus(GameStatus.EXIT)));

		super.texts.add(new Text(StringScreen.TEXT_FULL_SCREEN.getValue(), (super.game.getGameWidth() - 333) / 2, 420, Color.WHITE));
	}

}
