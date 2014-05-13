package game.api.placeboats;

import game.api.GameState;
import game.impl.BoardLocation;

import java.util.ArrayList;

public class PlaceBoatHorizontalDown implements PlaceBoatStrategy {
	private int xIndex;
	private int yIndex;
	private int shipSize;

	public PlaceBoatHorizontalDown(int xIndex, int yIndex, int shipSize) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.shipSize = shipSize;
	}

	@Override
	public ArrayList<BoardLocation> getLocations(GameState state) {
		return placeShip(state);
	}

	private ArrayList<BoardLocation> placeShip(GameState state) {
		ArrayList<BoardLocation> locations = new ArrayList<BoardLocation>();
		char player = state.getPlayerInTurn().getName().charAt(0);
		for (int y = yIndex; y < shipSize + yIndex; y++)
			for (BoardLocation bl : state.getBoard().getLocations())
				if (bl.getId().equals(player + coordinates[y] + xIndex)) {
					locations.add(bl);
				}
		return locations;
	}

}
