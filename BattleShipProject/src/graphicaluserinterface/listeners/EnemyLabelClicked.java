package graphicaluserinterface.listeners;

import graphicaluserinterface.GameGUI;
import graphicaluserinterface.gamelabels.GameLabelBlank;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class EnemyLabelClicked implements MouseListener {
	private GameLabelBlank label;

	private GameGUI gui;

	public EnemyLabelClicked(GameLabelBlank label, GameGUI gui) {
		this.label = label;
		this.gui = gui;

	}

	@Override
	public void mouseClicked(MouseEvent obj) {

	}

	@Override
	public void mouseEntered(MouseEvent obj) {
		Color color = new Color(255, 255, 255, 0x50);
		if (!label.hasClicked()) {
			label.setOpaque(true);
			label.changeColor(color);
			label.getParent().repaint();
		}

	}

	@Override
	public void mouseExited(MouseEvent obj) {
		if (!label.hasClicked()) {
			label.setOpaque(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent obj) {
		ArrayList<String> locations = new ArrayList<String>();
		locations.add(label.getCoordinate());		
		gui.setNextMove(locations);
	}

	@Override
	public void mouseReleased(MouseEvent obj) {

	}
}
