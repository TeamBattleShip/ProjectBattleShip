package game.io;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;
import graphicaluserinterface.GameGUI;

import java.util.ArrayList;

public class InputUnitBattleship extends InputUnit {
	private GameState state;
	private GameGUI gui;

	public InputUnitBattleship(GameGUI gui, InputListener listener) {
		this.gui = gui;
		addInputListener(listener);
	}

	public boolean makeMove() {
		System.out.println("hej");
		boolean makeMyMove = gui.getNextMove().get(0).charAt(0) == state
				.getLastPlayer().getName().charAt(0)
				&& !state.hasEnded();		
		if (makeMyMove) {
			notifyListenersOfMove(new Move(getPlayer(), getPiece(),
					makeDestinations()));
		}
		return makeMyMove;
	}

	private ArrayList<BoardLocation> makeDestinations() {
		ArrayList<String> location = gui.getNextMove();
		ArrayList<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		for (String s : location) {
			for (BoardLocation bl : state.getBoard().getLocations()) {

				if (bl.getId().equals(s))
					boardLocations.add(bl);
			}
		}
		return boardLocations;
	}

	private GamePiece getPiece() {
		ArrayList<String> location = gui.getNextMove();

		if (location.size() > 1) {
			return state.getPlayerInTurn().getPieces().get(0);
		} else {
			return state.getPlayerInTurn().getPieces().get(1);
		}
	}

	private Player getPlayer() {
		return state.getPlayerInTurn();
	}

	@Override
	public void setup(GameState state) {
		this.state = state;
		gui.setInputUnit(this);
	}
}
