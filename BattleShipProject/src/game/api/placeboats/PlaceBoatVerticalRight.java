package game.api.placeboats;

import game.api.GameState;
import game.impl.BoardLocation;

import java.util.ArrayList;

public class PlaceBoatVerticalRight implements PlaceBoatStrategy {
	private int xIndex;
	private int yIndex;
	private int shipSize;

	public PlaceBoatVerticalRight(int xIndex, int yIndex, int shipSize) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.shipSize = shipSize;
	}

	@Override
	public ArrayList<BoardLocation> getLocations(GameState state) {
		// TODO Auto-generated method stub
		return placeShip(state);
	}

	private ArrayList<BoardLocation> placeShip(GameState state) {
		ArrayList<BoardLocation> locations = new ArrayList<BoardLocation>();
		char player = state.getPlayerInTurn().getName().charAt(0);
		for (int x = xIndex; x < shipSize + xIndex; x++)
			for (BoardLocation bl : state.getBoard().getLocations())
				if (bl.getId().equals(player + coordinates[yIndex] + x)) {
					locations.add(bl);
				
				}
		return locations;
	}
}
