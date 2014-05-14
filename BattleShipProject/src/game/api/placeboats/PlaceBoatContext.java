package game.api.placeboats;

import java.util.HashMap;

public class PlaceBoatContext {
	private HashMap<String, PlaceBoatStrategy> container;
	private String key;

	public PlaceBoatContext(Boolean vertical, int xIndex, int yIndex,
			int shipSize) {


		container = new HashMap<>();
		if (vertical) {
			if (shipSize - xIndex < 0 || shipSize - xIndex > 10) {
				key = "VL";
			} else {
				key = "VR";
			}
		} else {
			if (shipSize + yIndex < 10) {
				key = "HD";
			} else {	
				key = "HU";
			}
		}
		container.put("HD", new PlaceBoatHorizontalDown(xIndex, yIndex,
				shipSize));
		container
				.put("HU", new PlaceBoatHorizontalUp(xIndex, yIndex, shipSize));
		container
				.put("VL", new PlaceBoatVerticalLeft(xIndex, yIndex, shipSize));
		container.put("VR",
				new PlaceBoatVerticalRight(xIndex, yIndex, shipSize));

	}

	public PlaceBoatStrategy getPlaceBoatStrategy() {

		return container.get(key);
	}
}
