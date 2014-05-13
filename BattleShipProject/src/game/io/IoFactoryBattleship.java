package game.io;

import game.api.GameStateBattleship;
import graphicaluserinterface.GameGUI;

public class IoFactoryBattleship implements IoFactory {
	private InputUnit inputUnit;
	private OutputUnit outputUnit;

	public IoFactoryBattleship(GameGUI gui, GameStateBattleship state) {
		outputUnit = new OutputUnitBattleship(gui);
		inputUnit = new InputUnitBattleship(gui, state);
	}

	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		return outputUnit;
	}
}
