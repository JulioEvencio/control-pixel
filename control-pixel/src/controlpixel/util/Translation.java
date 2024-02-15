package controlpixel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import controlpixel.Game;
import controlpixel.strings.GameString;
import controlpixel.strings.StringError;
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
				StringScreen.NEW_GAME,
				StringScreen.CREDITS,
				StringScreen.EXIT,
				StringScreen.TEXT_FULL_SCREEN,
				StringScreen.PROGRAMMER,
				StringScreen.PROGRAMMER_LINK,
				StringScreen.SPRITES_CREDITS,
				StringScreen.SPRITES_CREDITS_LINK,
				StringScreen.BACK,
				StringScreen.EXIT_GAME,
				StringScreen.YES,
				StringScreen.NO
			});

			Translation.toTranslation("level", new GameString[] {
				// Code
			});
		} catch (Exception e) {
			Game.exitWithError("Error loading files");
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