package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import game.api.GameState;
import game.api.placeboats.PlaceBoatContext;
import game.api.placeboats.PlaceBoatStrategy;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Player;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PlaceBoatContextTest {
	PlaceBoatContext pl1, pl2, pl3, pl4;
	GameState state;
	Board board;
	Player player;
	ArrayList<BoardLocation> list2;

	@Before
	public void setUp() throws Exception {
		state = mock(GameState.class);
		board = mock(Board.class);
		player = new Player("Player", new ArrayList<GamePiece>(Arrays.asList(
				new GamePiece("S"), new GamePiece("B"))));
		when(state.getBoard()).thenReturn(board);
		when(state.getPlayerInTurn()).thenReturn(player);
		String[] coordinates = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J" };
		ArrayList<BoardLocation> list = new ArrayList<BoardLocation>();
		for (String s : coordinates)
			for (int i = 1; i < 12; i++) {
				list.add(new BoardLocation("P" + s + i));

			}
		when(board.getLocations()).thenReturn(list);

		pl1 = new PlaceBoatContext(true, 1, 0, 2);
		pl2 = new PlaceBoatContext(true, 10, 1, 3);
		pl3 = new PlaceBoatContext(false, 3, 1, 5);
		pl4 = new PlaceBoatContext(false, 2, 9, 4);
	}

	@Test
	public void testPlaceRight() {
		list2 = pl1.getPlaceBoatStrategy().getLocations(state);
		assertEquals("PA1", list2.get(0).getId());
		assertEquals("PA2", list2.get(1).getId());

	}

	@Test
	public void testPlaceLeft() {
		list2 = pl2.getPlaceBoatStrategy().getLocations(state);
		assertEquals("PB10", list2.get(0).getId());
		assertEquals("PB9", list2.get(1).getId());
		assertEquals("PB8", list2.get(2).getId());

	}

	@Test
	public void testPlaceUp() {
		list2 = pl4.getPlaceBoatStrategy().getLocations(state);
		assertEquals("PJ2", list2.get(0).getId());
		assertEquals("PI2", list2.get(1).getId());
		assertEquals("PH2", list2.get(2).getId());
		assertEquals("PG2", list2.get(3).getId());

	}

	@Test
	public void testPlaceDown() {
		list2 = pl3.getPlaceBoatStrategy().getLocations(state);
		assertEquals("PB3", list2.get(0).getId());
		assertEquals("PC3", list2.get(1).getId());
		assertEquals("PD3", list2.get(2).getId());
		assertEquals("PE3", list2.get(3).getId());
		assertEquals("PF3", list2.get(4).getId());
	}
}
