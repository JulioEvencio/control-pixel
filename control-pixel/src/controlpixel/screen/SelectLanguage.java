package controlpixel.screen;

import java.awt.Color;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.Text;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Translation;
import controlpixel.util.Translation.Language;
import controlpixel.util.Util;

public class SelectLanguage extends Screen {

	public SelectLanguage(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(game, StringScreen.ENGLISH.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 120, () -> this.selectLanguage(Language.ENGLISH, game)));
		super.buttons.add(new Button(game, StringScreen.PORTUGUESE.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 220, () -> this.selectLanguage(Language.PORTUGUESE, game)));
		super.buttons.add(new Button(game, StringScreen.SPANISH.getValue(), (game.getGameWidth() - Button.getWidthPressed()) / 2, 320, () -> this.selectLanguage(Language.SPANISH, game)));

		super.texts.add(new Text(StringScreen.TEXT_FULL_SCREEN.getValue(), (game.getGameWidth() - Util.getWidthTextFullScreen()) / 2, 420, Color.WHITE));
	}

	private void selectLanguage(Language language, Game game) {
		Translation.changeTheLanguage(language);

		game.initializeScreen();
		game.updateGameStatus(GameStatus.MAIN_MENU);
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.SELECT_LANGUAGE;
	}

}
