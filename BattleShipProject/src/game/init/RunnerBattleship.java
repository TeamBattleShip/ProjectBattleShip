package game.init;

import game.api.GameStateBattleship;
import game.io.IoFactoryBattleship;
import graphicaluserinterface.GameGUI;

public class RunnerBattleship {

	public RunnerBattleship() {
		GameGUI gui = new GameGUI();
		GameStateBattleship state = new GameStateBattleship();
		new Runner(state, new IoFactoryBattleship(gui, state)).run();
	}
}
