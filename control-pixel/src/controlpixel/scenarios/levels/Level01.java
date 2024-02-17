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
			{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
			{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B'},
			{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B'},
			{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B'},
			{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B'},
			{'B', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B'},
			{'B', 'B', 'B', 'B', 'B', 'B', 'B', ' ', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
			{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
			{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}
		};
	}

	protected Scenario restartScenario() {
		return new Level01(super.game);
	}

}
