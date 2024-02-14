package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;

public class Credits extends Screen {

	public Credits(Game game) {
		super(game, StringScreen.CREDITS.getValue());

		super.texts.add(new Text(StringScreen.PROGRAMMER.getValue(), 50, 130, Color.WHITE));
		super.texts.add(new Text(StringScreen.PROGRAMMER_LINK.getValue(), 50, 160, Color.WHITE));

		super.texts.add(new Text(StringScreen.SPRITES_CREDITS.getValue(), 50, 230, Color.WHITE));
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_LINK.getValue(), 50, 260, Color.WHITE));

		super.buttons.add(new Button(super.game, StringScreen.BACK.getValue(), 50, game.getGameHeight() - Button.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.MAIN_MENU)));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CREDITS;
	}

}
