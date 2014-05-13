package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.api.GameState;
import game.api.GameStateBattleship;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnitBattleship;
import graphicaluserinterface.GameGUI;
import graphicaluserinterface.gamelabels.GameLabelBlank;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class OutputUnitTest {

	GameGUI gui;
	GameState state;
	OutputUnitBattleship output;
	HashMap<String, GameLabelBlank> playerBoard, enemyBoard;
	Board board;
	ArrayList<BoardLocation> locations;
	BoardLocation a1, a2, a3, b1, b2, b3;

	@Before
	public void setUp() throws Exception {
		playerBoard = new HashMap<>();
		enemyBoard = new HashMap<>();

		locations = new ArrayList<>();

		gui = mock(GameGUI.class);
		state = mock(GameStateBattleship.class);
		board = mock(Board.class);

		a1 = mock(BoardLocation.class);
		a2 = mock(BoardLocation.class);
		a3 = mock(BoardLocation.class);
		b1 = mock(BoardLocation.class);
		b2 = mock(BoardLocation.class);
		b3 = mock(BoardLocation.class);

		locations.add(a1);
		locations.add(a2);
		locations.add(a3);
		locations.add(b1);
		locations.add(b2);
		locations.add(b3);

		when(a1.getId()).thenReturn("PA1");
		when(a2.getId()).thenReturn("PA2");
		when(a3.getId()).thenReturn("PA3");
		when(b1.getId()).thenReturn("EB1");
		when(b2.getId()).thenReturn("EB2");
		when(b3.getId()).thenReturn("EB3");

		when(a1.getPiece()).thenReturn(new GamePiece("H"));
		when(a2.getPiece()).thenReturn(new GamePiece("M"));
		when(a3.getPiece()).thenReturn(new GamePiece("S"));
		when(b1.getPiece()).thenReturn(new GamePiece("H"));
		when(b2.getPiece()).thenReturn(new GamePiece("M"));
		when(b3.getPiece()).thenReturn(new GamePiece("S"));

		when(state.getBoard()).thenReturn(board);
		when(board.getLocations()).thenReturn(locations);

		JPanel panel = new JPanel();

		playerBoard.put("PA1", new GameLabelBlank("PA1"));
		playerBoard.put("PA2", new GameLabelBlank("PA2"));
		playerBoard.put("PA3", new GameLabelBlank("PA3"));
		playerBoard.put("PB1", new GameLabelBlank("PB1"));
		playerBoard.put("PB2", new GameLabelBlank("PB2"));
		playerBoard.put("PB3", new GameLabelBlank("PB3"));

		enemyBoard.put("EA1", new GameLabelBlank("EA1"));
		enemyBoard.put("EA2", new GameLabelBlank("EA2"));
		enemyBoard.put("EA3", new GameLabelBlank("EA3"));
		enemyBoard.put("EB1", new GameLabelBlank("EB1"));
		enemyBoard.put("EB2", new GameLabelBlank("EB2"));
		enemyBoard.put("EB3", new GameLabelBlank("EB3"));

		panel.add(playerBoard.get("PA1"));
		panel.add(playerBoard.get("PA2"));
		panel.add(playerBoard.get("PA3"));
		panel.add(playerBoard.get("PB1"));
		panel.add(playerBoard.get("PB2"));
		panel.add(playerBoard.get("PB3"));

		panel.add(enemyBoard.get("EA1"));
		panel.add(enemyBoard.get("EA2"));
		panel.add(enemyBoard.get("EA3"));
		panel.add(enemyBoard.get("EB1"));
		panel.add(enemyBoard.get("EB2"));
		panel.add(enemyBoard.get("EB3"));

		output = new OutputUnitBattleship(gui);
		output.publish(state);
		output.update(playerBoard, enemyBoard);
	}

	@Test
	public void test() {

		assertEquals(new Color(0, 255, 0, 0x50), playerBoard.get("PA1")
				.getColor());
		assertEquals(new Color(255, 0, 0, 0x50), playerBoard.get("PA2")
				.getColor());
		assertEquals(null, playerBoard.get("PA3").getColor());

		assertEquals(new Color(0, 255, 0, 0x50), enemyBoard.get("EB1")
				.getColor());
		assertEquals(new Color(255, 0, 0, 0x50), enemyBoard.get("EB2")
				.getColor());
		assertEquals(null, enemyBoard.get("EA3").getColor());
	}

}
