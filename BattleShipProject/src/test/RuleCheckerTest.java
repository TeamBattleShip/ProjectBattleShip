package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.api.GameState;
import game.api.RuleChecker;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RuleCheckerTest {
	RuleChecker rules;
	Move move;
	Move move1;
	GameState state;
	ArrayList<BoardLocation> locations;
	BoardLocation a1, a2, a3, b1, b2, b3;

	Board board;

	@Before
	public void setUp() throws Exception {
		move = mock(Move.class);
		move1 = mock(Move.class);
		state = mock(GameState.class);
		rules = new RuleChecker();
		locations = new ArrayList<>();
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
		when(b1.getId()).thenReturn("PB1");
		when(b2.getId()).thenReturn("PB2");
		when(b3.getId()).thenReturn("PB3");

		when(a1.getPiece()).thenReturn(new GamePiece("S"));
		when(a2.getPiece()).thenReturn(new GamePiece("S"));
		when(a3.getPiece()).thenReturn(new GamePiece("S"));
		when(b1.getPiece()).thenReturn(null);
		when(b2.getPiece()).thenReturn(null);
		when(b3.getPiece()).thenReturn(null);

		when(state.getBoard()).thenReturn(board);

		when(board.getLocations()).thenReturn(locations);

		BoardLocation A1, A2, A3, B1, B2, B3;
		A1 = new BoardLocation("PA1");
		A2 = new BoardLocation("PA2");
		A3 = new BoardLocation("PA3");
		B1 = new BoardLocation("PB1");
		B2 = new BoardLocation("PB2");
		B3 = new BoardLocation("PB3");

		A1.addPiece(new GamePiece("S"));
		A2.addPiece(new GamePiece("S"));
		A3.addPiece(new GamePiece("S"));
		B1.addPiece(new GamePiece("B"));
		B2.addPiece(new GamePiece("B"));
		B3.addPiece(new GamePiece("B"));

		when(move.getDestinations()).thenReturn(Arrays.asList(A1, A2, A3));

		when(move1.getDestinations()).thenReturn(Arrays.asList(B1, B2, B3));
	}

	@Test
	public void isValideMoveTest() {
		assertEquals(false, rules.isValidMove(move, state));
		assertEquals(true, rules.isValidMove(move1, state));
	}

	@Test
	public void isGameFinishedTest() {
		assertEquals(true, rules.isGameFinishied(17, 5));
		assertEquals(true, rules.isGameFinishied(3, 17));
		assertEquals(false, rules.isGameFinishied(7, 5));
	}
}
