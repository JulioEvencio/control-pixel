package controlpixel.scenarios.entities;

import java.awt.Color;

import controlpixel.scenarios.Scenario;
import controlpixel.util.Rect;

public class Player {

	private final Rect rect;

	private double speedX;
	private double speedY;

	private final Color color;

	private final Scenario scenario;

	public Player(Scenario scenario) {
		this.rect = new Rect(-50, -50, 0, 0);

		this.color = new Color(192, 203, 220);

		this.scenario = scenario;
	}

}
