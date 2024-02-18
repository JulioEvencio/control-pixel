package controlpixel.scenarios.levels;

import controlpixel.Game;
import controlpixel.scenarios.Scenario;

public class Level01 extends Scenario {

	public Level01(Game game) {
		super(game);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'L', 'C', 'R', ' ', 'L', 'C', 'C', 'C', 'C', 'C', 'R', ' ', 'L', 'C', 'C'},
			{' ', 'F', 'G', 'H', ' ', 'F', 'G', 'G', 'G', 'G', 'G', 'H', ' ', 'F', 'G', 'G'},
			{'W', 'F', 'G', 'H', 'W', 'F', 'G', 'G', 'G', 'G', 'G', 'H', 'W', 'F', 'G', 'G'},
		};
	}

	protected Scenario restartScenario() {
		return new Level01(super.game);
	}

}
