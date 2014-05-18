package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.api.GameState;
import game.api.GameStateBattleship;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Player;
import game.io.InputListener;
import game.io.InputUnitBattleship;
import graphicaluserinterface.GameGUI;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class InputUnitBattleshipTest {
	InputUnitBattleship input;
	GameState state;
	InputListener listener;
	GameGUI gui;
	Board board;

	@Before
	public void setUp() throws Exception {
		GameStateBattleship gamestate = mock(GameStateBattleship.class);
		gui = mock(GameGUI.class);
		state = gamestate;
		listener = gamestate;
		input = new InputUnitBattleship(gui, listener);

		when(gui.getNextMove()).thenReturn(
				new ArrayList<String>(Arrays.asList("PA1", "PA2", "PA3")));
		when(state.getPlayerInTurn()).thenReturn(
				new Player("Player", new ArrayList<GamePiece>(Arrays.asList(
						new GamePiece("S"), new GamePiece("B")))));
		when(state.hasEnded()).thenReturn(false);

		board = mock(Board.class);
		when(state.getBoard()).thenReturn(board);
		when(board.getLocations()).thenReturn(
				new ArrayList<BoardLocation>(Arrays.asList(new BoardLocation(
						"PA1"), new BoardLocation("PA2"), new BoardLocation(
						"PA3"))));
		input.setup(state);

	}

	@Test
	public void test() {
		

	}

}
