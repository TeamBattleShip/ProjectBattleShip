package game.api.placeboats;

import game.api.GameState;
import game.impl.BoardLocation;

import java.util.ArrayList;

public class PlaceBoatVerticalLeft implements PlaceBoatStrategy {
	private int xIndex;
	private int yIndex;
	private int shipSize;

	public PlaceBoatVerticalLeft(int xIndex, int yIndex, int shipSize) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.shipSize = shipSize;
	}

	@Override
	public ArrayList<BoardLocation> getLocations(GameState state) {
		return placeShip(state);
	}

	private ArrayList<BoardLocation> placeShip(GameState state) {
		char player = state.getPlayerInTurn().getName().charAt(0);
		int temp2 = xIndex - shipSize;
		ArrayList<BoardLocation> locations = new ArrayList<BoardLocation>();
		for (int x = xIndex; x > temp2; x--) {
			for (BoardLocation bl : state.getBoard().getLocations())
				if (bl.getId().equals("" + player + coordinates[yIndex] + x)) {

					locations.add(bl);
				}

		}
		return locations;
	}
}
