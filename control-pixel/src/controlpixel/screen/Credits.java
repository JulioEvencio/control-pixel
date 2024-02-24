package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.ButtonSmall;
import controlpixel.gui.Text;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;

public class Credits extends Screen {

	public Credits(Game game) {
		super(game, StringScreen.CREDITS.getValue());

		super.texts.add(new Text(StringScreen.PROGRAMMER.getValue(), 50, 130, Color.WHITE));
		super.texts.add(new Text(StringScreen.PROGRAMMER_LINK.getValue(), 50, 150, Color.WHITE));
		
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_GUI.getValue(), 430, 130, Color.WHITE));
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_LINK_GUI.getValue(), 430, 150, Color.WHITE));

		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_MAP.getValue(), 50, 180, Color.WHITE));
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_LINK_MAP.getValue(), 50, 200, Color.WHITE));

		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_PLAYER.getValue(), 430, 180, Color.WHITE));
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_LINK_PLAYER.getValue(), 430, 200, Color.WHITE));
		
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_PORTAL.getValue(), 50, 230, Color.WHITE));
		super.texts.add(new Text(StringScreen.SPRITES_CREDITS_LINK_PORTAL.getValue(), 50, 250, Color.WHITE));

		super.buttonSmalls.add(new ButtonSmall(game, StringScreen.BACK.getValue(), 50, game.getGameHeight() - Button.getHeightpressed() - 50, () -> game.updateGameStatus(GameStatus.MAIN_MENU)));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CREDITS;
	}

}
