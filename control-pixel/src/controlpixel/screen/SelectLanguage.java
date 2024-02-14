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

public class SelectLanguage extends Screen {

	public SelectLanguage(Game game) {
		super(game, StringGame.TITLE.getValue());

		super.buttons.add(new Button(super.game, StringScreen.ENGLISH.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 120, () -> this.selectLanguage(Language.ENGLISH)));
		super.buttons.add(new Button(super.game, StringScreen.PORTUGUESE.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 220, () -> this.selectLanguage(Language.PORTUGUESE)));
		super.buttons.add(new Button(super.game, StringScreen.SPANISH.getValue(), (super.game.getGameWidth() - Button.getWidthPressed()) / 2, 320, () -> this.selectLanguage(Language.SPANISH)));

		super.texts.add(new Text(StringScreen.TEXT_FULL_SCREEN.getValue(), (super.game.getGameWidth() - 333) / 2, 420, Color.WHITE));
	}

	private void selectLanguage(Language language) {
		Translation.changeTheLanguage(language);

		super.game.initializeScreen();
		super.game.updateGameStatus(GameStatus.MAIN_MENU);
	}

}
