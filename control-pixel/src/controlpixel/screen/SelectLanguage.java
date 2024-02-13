package controlpixel.screen;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.strings.StringGame;
import controlpixel.strings.StringScreen;
import controlpixel.util.GameStatus;
import controlpixel.util.Translation;
import controlpixel.util.Translation.Language;

public class SelectLanguage extends Screen {

	public SelectLanguage(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(super.game, StringScreen.ENGLISH.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 150, () -> this.selectLanguage(Language.ENGLISH)));
		super.buttons.add(new Button(super.game, StringScreen.PORTUGUESE.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 250, () -> this.selectLanguage(Language.PORTUGUESE)));
		super.buttons.add(new Button(super.game, StringScreen.SPANISH.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 350, () -> this.selectLanguage(Language.SPANISH)));
	}

	private void selectLanguage(Language language) {
		Translation.changeTheLanguage(language);

		super.game.initializeScreen();
		super.game.updateGameStatus(GameStatus.MAIN_MENU);
	}

}
