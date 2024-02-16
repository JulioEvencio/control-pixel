package controlpixel.scenarios.tiles;

import controlpixel.util.CustomColors;
import controlpixel.util.Rect;

public class Block extends Tile {

	public Block(int x, int y) {
		super(new Rect(x, y, 50, 50), CustomColors.PURPLE);
	}

}
