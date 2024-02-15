package controlpixel.util;

import java.awt.Font;

public class Util {

	public static Font getFontTitle() {
		return new Font("Arial", Font.BOLD, 36);
	}

	public static Font getFontDefault() {
		return new Font("Arial", Font.BOLD, 16);
	}

	public static int getWidthTextFullScreen() {
		if (Translation.getLanguage().equals("portuguese")) {
			return 395;
		} else if (Translation.getLanguage().equals("spanish")) {
			return 515;
		} else {
			return 333;
		}
	}

	public static int getWidthExitGame() {
		if (Translation.getLanguage().equals("portuguese")) {
			return 209;
		} else if (Translation.getLanguage().equals("spanish")) {
			return 261;
		} else {
			return 288;
		}
	}

}
