package controlpixel.util;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controlpixel.Game;
import controlpixel.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheet;

	static {
		BufferedImage auxSpritesheet = null;

		try {
			auxSpritesheet = ImageIO.read(Spritesheet.class.getResource("/sprites/sprites.png"));
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheet = auxSpritesheet;
	}

	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return Spritesheet.spritesheet.getSubimage(x, y, width, height);
	}

}
