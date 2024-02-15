package controlpixel.scenarios.tiles;

import java.awt.Color;

import controlpixel.util.Rect;

public class Block extends Tile {

	public Block(int x, int y) {
		super(new Rect(x, y, 50, 50), new Color(50, 50, 50));
	}

}
