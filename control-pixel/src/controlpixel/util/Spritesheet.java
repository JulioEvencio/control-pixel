package controlpixel.util;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controlpixel.Game;
import controlpixel.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;
	private static final BufferedImage spritesheetMap;
	private static final BufferedImage spritesheetPlayer;
	private static final BufferedImage spritesheetPortal;

	static {
		BufferedImage auxSpritesheetGUI = null;
		BufferedImage auxSpritesheetMap = null;
		BufferedImage auxSpritesheetPlayer = null;
		BufferedImage auxSpritesheetPortal = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/gui.png"));
			auxSpritesheetMap = ImageIO.read(Spritesheet.class.getResource("/sprites/map.png"));
			auxSpritesheetPlayer = ImageIO.read(Spritesheet.class.getResource("/sprites/player.png"));
			auxSpritesheetPortal = ImageIO.read(Spritesheet.class.getResource("/sprites/portal.png"));
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
		spritesheetMap = auxSpritesheetMap;
		spritesheetPlayer = auxSpritesheetPlayer;
		spritesheetPortal = auxSpritesheetPortal;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteMap(int x, int y, int width, int height) {
		return Spritesheet.spritesheetMap.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpritePlayer(int x, int y, int width, int height) {
		return Spritesheet.spritesheetPlayer.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpritePortal(int x, int y, int width, int height) {
		return Spritesheet.spritesheetPortal.getSubimage(x, y, width, height);
	}

}
