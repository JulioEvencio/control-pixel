package controlpixel.scenarios.tiles;

import java.awt.Graphics;

import controlpixel.util.Rect;

public abstract class Tile {

	public abstract Rect getRect();

	public abstract void render(Graphics render);

}
