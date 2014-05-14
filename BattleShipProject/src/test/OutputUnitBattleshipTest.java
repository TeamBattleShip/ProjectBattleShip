package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import javax.swing.JLabel;

import game.api.GameState;
import game.api.GameStateBattleship;
import game.impl.BoardLocation;
import game.io.OutputUnitBattleship;
import graphicaluserinterface.GameGUI;
import graphicaluserinterface.gamelabels.GameLabelBlank;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OutputUnitBattleshipTest {
	OutputUnitBattleship output;
	GameGUI gui;
	HashMap<String, GameLabelBlank> playerBoard;
	HashMap<String, GameLabelBlank> enemyBoard;
	@Before
	public void setUp() throws Exception {
		gui = Mockito.mock(GameGUI.class);
		playerBoard = new HashMap<String, GameLabelBlank>();
		enemyBoard = new HashMap<String, GameLabelBlank>();
		output = new OutputUnitBattleship(gui);
		System.out.println(playerBoard + "  " + enemyBoard);
		output.update(playerBoard, enemyBoard);
 
		
	}

	@Test
	public void test() {
		output.update(playerBoard, enemyBoard);
		
	}

}
