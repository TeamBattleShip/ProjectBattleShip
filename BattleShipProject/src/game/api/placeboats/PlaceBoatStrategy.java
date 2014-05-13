package game.api.placeboats;

import game.api.GameState;
import game.impl.BoardLocation;

import java.util.ArrayList;

public interface PlaceBoatStrategy {
	final String[] coordinates = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J" };

	public ArrayList<BoardLocation> getLocations(GameState state);

}
