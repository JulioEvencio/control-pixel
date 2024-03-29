package controlpixel.util;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

public class Util {

	private static Save save;

	private static final String SAVE_FILE = "save.obj";
	private static final String SAVE_FOLDER = "data";

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

	public static void saveData() {
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(Util.getFileDirectory()))) {
			out.writeObject(Util.save);
		} catch (Exception e) {
			// Code
		}
	}

	public static void loadData() {
		try (ObjectInput in = new ObjectInputStream(new FileInputStream(Util.getFileDirectory()))) {
			Util.save = (Save) in.readObject();
		} catch (Exception e) {
			Util.save = new Save();
			Util.saveData();
		}
	}

	public static Save getSave() {
		return Util.save;
	}

	private static String getFileDirectory() throws URISyntaxException {
		String jarDir = new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		File folderDir = new File(jarDir + File.separator + Util.SAVE_FOLDER);

		if (!folderDir.exists()) {
			folderDir.mkdirs();
		}

		return jarDir + File.separator + Util.SAVE_FOLDER + File.separator + Util.SAVE_FILE;
	}

}
