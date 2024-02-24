package controlpixel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import controlpixel.Game;
import controlpixel.strings.GameString;
import controlpixel.strings.StringError;
import controlpixel.strings.StringLevel;
import controlpixel.strings.StringScreen;

public class Translation {

	private static String language = "english";

	public enum Language {
		ENGLISH, PORTUGUESE, SPANISH;
	}

	public static String getLanguage() {
		return Translation.language;
	}

	public static void changeTheLanguage(Language language) {
		if (language == Translation.Language.PORTUGUESE) {
			Translation.language = "portuguese";
		} else if (language == Translation.Language.SPANISH) {
			Translation.language = "spanish";
		} else {
			Translation.language = "english";
		}

		try {
			Translation.toTranslation("error", new GameString[] {
				StringError.ERROR,
				StringError.ERROR_LOADING_SPRITES,
				StringError.ERROR_LOADING_AUDIO
			});

			Translation.toTranslation("screen", new GameString[] {
				StringScreen.PLAY,
				StringScreen.CREDITS,
				StringScreen.EXIT,
				StringScreen.TEXT_FULL_SCREEN,
				StringScreen.PROGRAMMER,
				StringScreen.PROGRAMMER_LINK,
				StringScreen.SPRITES_CREDITS_GUI,
				StringScreen.SPRITES_CREDITS_LINK_GUI,
				StringScreen.SPRITES_CREDITS_MAP,
				StringScreen.SPRITES_CREDITS_LINK_MAP,
				StringScreen.SPRITES_CREDITS_PLAYER,
				StringScreen.SPRITES_CREDITS_LINK_PLAYER,
				StringScreen.SPRITES_CREDITS_PORTAL,
				StringScreen.SPRITES_CREDITS_LINK_PORTAL,
				StringScreen.AUDIO_MENU_CREDITS,
				StringScreen.AUDIO_MENU_CREDITS_LINK,
				StringScreen.AUDIO_GAME_CREDITS,
				StringScreen.AUDIO_GAME_CREDITS_LINK,
				StringScreen.SOURCE_CODE_LINK,
				StringScreen.NEXT,
				StringScreen.BACK,
				StringScreen.EXIT_GAME,
				StringScreen.YES,
				StringScreen.NO,
				StringScreen.CONTINUE,
				StringScreen.MENU,
				StringScreen.BLOCKED
			});

			Translation.toTranslation("level", new GameString[] {
					StringLevel.TUTORIAL_HELP_PIXEL,
					StringLevel.TUTORIAL_USE_MOUSE_BLOCK,
					StringLevel.TUTORIAL_EXIT_BUILD_MODE,
					StringLevel.TUTORIAL_RESTART,
					StringLevel.TUTORIAL_PAUSE,
					StringLevel.TUTORIAL_FULL_SCREEN,
					StringLevel.TUTORIAL_FPS,
					StringLevel.TUTORIAL_MUSIC,
					StringLevel.TUTORIAL_BLOCK_PATH,
					StringLevel.TUTORIAL_SCROLL,
					StringLevel.TUTORIAL_CRYSTAL_GREEN,
					StringLevel.TUTORIAL_PORTAL_SIDE,
					StringLevel.TUTORIAL_CRYSTAL_BLUE,
					StringLevel.TUTORIAL_LEVEL_BIG,
					StringLevel.TUTORIAL_MOVE_CAMERA
			});
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}
	}

	private static void toTranslation(String file, GameString[] messageString) throws IOException {
		String fileName = String.format("/language/%s/%s.txt", Translation.language, file);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Translation.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			for (int i = 0; i < messageString.length; i++) {
				messageString[i].setValue(Translation.readLine(reader));
			}
		}
	}

	private static String readLine(BufferedReader reader) throws IOException {
		String content = reader.readLine();

		if (content == null) {
			throw new IOException();
		}

		return content;
	}

}
