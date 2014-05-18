package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import game.api.GameStateBattleship;
import game.impl.BoardLocation;
import game.impl.Move;

import org.junit.Before;
import org.junit.Test;

public class GameStateTest {
	GameStateBattleship state;

	@Before
	public void setUp() throws Exception {
		state = new GameStateBattleship(0);
	}

	@Test
	public void testBoard() {
		final String[] coordinates = { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J" };
		int x = 0;
		for (String s : coordinates)
			for (int i = 1; i < 11; i++) {
				if (state.getBoard().getLocations().get(x).getId().charAt(0) == 'P')
					assertEquals("P" + s + i, state.getBoard().getLocations()
							.get(x).getId());
				else
					assertEquals("E" + s + i, state.getBoard().getLocations()
							.get(x).getId());
				x++;
			}
	}

	@Test
	public void testRandomPlacements() {
		GameStateBattleship state2 = new GameStateBattleship();
		int x = 0;
		for (BoardLocation bl : state2.getBoard().getLocations())
			if (bl.getPiece() != null)
				x++;
		assertEquals(34, x);
	}

	@Test
	public void testProposeMove() {
		assertEquals(true, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(0),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(0), state.getBoard().getLocations()
						.get(1), state.getBoard().getLocations().get(2))))));

		assertEquals(true, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(0),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(101), state.getBoard()
						.getLocations().get(102), state.getBoard()
						.getLocations().get(103))))));

		assertEquals(true, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(1),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(0))))));

		assertEquals(true, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(1),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(101))))));

		assertEquals(false, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(1),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(0))))));

		assertEquals(false, state.proposeMove(new Move(state.getPlayerInTurn(),
				state.getPlayerInTurn().getPieces().get(1),
				new ArrayList<BoardLocation>(Arrays.asList(state.getBoard()
						.getLocations().get(101))))));
	}
}
