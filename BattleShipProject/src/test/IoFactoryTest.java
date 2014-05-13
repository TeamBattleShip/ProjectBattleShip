package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import game.api.GameStateBattleship;
import game.io.InputListener;
import game.io.InputUnit;
import game.io.InputUnitBattleship;
import game.io.IoFactory;
import game.io.IoFactoryBattleship;
import game.io.OutputUnit;
import graphicaluserinterface.GameGUI;

import org.junit.Before;
import org.junit.Test;

public class IoFactoryTest {

	OutputUnit output;
	GameGUI gui;
	InputUnit input;
	InputListener listener;
	IoFactory factory;
	GameStateBattleship state;

	@Before
	public void setUp() throws Exception {
		gui = mock(GameGUI.class);
		state = mock(GameStateBattleship.class);
		input = new InputUnitBattleship(gui, listener);
		factory = new IoFactoryBattleship(gui, state);
	}

	@Test
	public void test() {
		assertEquals(true,factory.getInputUnit()!=null);
		assertEquals(true,factory.getOutputUnit()!=null);
	}
}
