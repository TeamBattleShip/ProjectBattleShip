package game.api;

import game.impl.BoardLocation;
import game.impl.Move;

public class RuleChecker {

	public boolean isValidMove(Move move, GameState state) {
		if(move == null)
			return false;
		if (!(move.getPlayer().getName()
				.equals(state.getPlayerInTurn().getName()) && (move
				.getDestinations().get(0).getId().charAt(0) == state
				.getLastPlayer().getName().charAt(0))))
			return false;
		boolean temp = false;
		if (move.getDestinations().size() > 1) {
			for (BoardLocation bl : move.getDestinations()) {
				for (BoardLocation blstate : state.getBoard().getLocations())
					if (bl.getId().equals(blstate.getId())) {
						if (blstate.getPiece() == null) {
							temp = true;
						} else {
							return false;
						}
					}
			}
		} else {
			for (BoardLocation bl : state.getBoard().getLocations())
				if (move.getDestinations().get(0).getId().equals(bl.getId())) {

					if (bl.getPiece() != null
							&& !bl.getPiece().getId().equals("S")) {
						return false;
					} else
						return true;
				}
		}
		return temp;
	}

	public Boolean isGameFinishied(int enemyHits, int playerHits) {
		return (enemyHits == 17) || (playerHits == 17);

	}

}
