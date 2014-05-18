package game.io;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import graphicaluserinterface.GameGUI;
import graphicaluserinterface.gamelabels.GameLabelBlank;

import java.awt.Color;
import java.util.HashMap;

public class OutputUnitBattleship implements OutputUnit {

	private GameGUI gui;
	private GameState state;

	public OutputUnitBattleship(GameGUI gui) {
		this.gui = gui;
	}

	@Override
	public void publish(GameState state) {
		this.state = state;
		gui.setOutput(this);
		gui.update();
	}

	public void update(HashMap<String, GameLabelBlank> playerBoard,
			HashMap<String, GameLabelBlank> enemyBoard) {
		for (BoardLocation bl : state.getBoard().getLocations()) {
			if (bl.getPiece() != null) {
				if (bl.getId().charAt(0) == 'P')
					paint(playerBoard.get(bl.getId()), bl.getPiece());
				if (bl.getId().charAt(0) == 'E')
					paint(enemyBoard.get(bl.getId()), bl.getPiece());
			}
		}
	}

	private void paint(GameLabelBlank gameLabelBlank, GamePiece piece) {

		if (piece.getId().equals("H")) {
			gameLabelBlank.changeColor(new Color(0, 255, 0, 0x50));
			gameLabelBlank.getParent().repaint();
			gameLabelBlank.click();
		}
		if (piece.getId().equals("M")) {
			gameLabelBlank.changeColor(new Color(255, 0, 0, 0x50));
			gameLabelBlank.getParent().repaint();
			gameLabelBlank.click();
		}
		if (piece.getId().equals("S") && state.hasEnded() && !gameLabelBlank.hasClicked()) {
			gameLabelBlank.changeColor(Color.blue);
			gameLabelBlank.click();

		}

	}

	public boolean hasEnded() {
		return state.hasEnded();
	}

	public String getWinner() {	
		return state.getWinner().getName();
	}

	public String getMessage() {	
		return state.getMessage();
	}
}
