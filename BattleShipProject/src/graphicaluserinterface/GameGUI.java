package graphicaluserinterface;

import game.io.InputUnitBattleship;
import game.io.OutputUnitBattleship;
import graphicaluserinterface.gamelabels.GameLabelBlank;
import graphicaluserinterface.gamelabels.GameLabelContext;
import graphicaluserinterface.gamelabels.GameLabelStrategy;
import graphicaluserinterface.listeners.EnemyLabelClicked;
import graphicaluserinterface.listeners.ExitGameOption;
import graphicaluserinterface.robot.TextTypingRobot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameGUI extends JFrame {
	private static final long serialVersionUID = 2489115114527559418L;
	private HashMap<String, GameLabelBlank> playerBoard;
	private HashMap<String, GameLabelBlank> enemyBoard;
	private JPanel enemyPanel;
	private JPanel playerPanel;
	private JPanel panelHolder;
	private JPanel mainPanel;
	private JLabel destroyer = new JLabel("1");
	private JLabel cruiser = new JLabel("2");
	private JLabel submarine = new JLabel("3");
	private JLabel battleship = new JLabel("4");
	private JLabel aircraftCarrier = new JLabel("5");
	private JButton exit = new JButton();
	private JPanel boats = new JPanel();
	private JTextArea statusText;
	private ArrayList<String> location;
	private InputUnitBattleship input;
	private OutputUnitBattleship output;
	private TextTypingRobot typer;

	public GameGUI() {
		location = new ArrayList<String>();
		publish();
	}

	public void publish() {
		setVisible(true);
		playerBoard = new HashMap<String, GameLabelBlank>();
		enemyBoard = new HashMap<String, GameLabelBlank>();
		setFrameProperties();
		makeJPanels1();
		makeAllJLabels();
		makeMidPanel();
	}

	private void setFrameProperties() {
		setResizable(false);
		setBounds(100, 100, 1500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private void makeJPanels1() {

		panelHolder = new JPanel();
		getContentPane().add(panelHolder, BorderLayout.CENTER);
		panelHolder.setLayout(new GridLayout(0, 3, 0, 0));

		try {
			playerPanel = new GamePanel(ImageIO.read(new File(
					"Pictures//Ocean2.jpg")));
			enemyPanel = new GamePanel(ImageIO.read(new File(
					"Pictures//Ocean2.jpg")));
			mainPanel = new GamePanel2(ImageIO.read(new File(
					"Pictures//MidPanelPic.jpg")));

		} catch (IOException e) {

		}
		enemyPanel.setLayout(new GridLayout(12, 12, 0, 0));
		panelHolder.add(playerPanel);

		panelHolder.add(mainPanel);
		playerPanel.setLayout(new GridLayout(12, 12, 0, 0));

		panelHolder.add(enemyPanel);

	}

	private void makeAllJLabels() {
		for (int i = 0, i2 = 0; i2 < 12; i = (i + 1) % 12) {
			makeJLabel(i2, i);
			if (i == 11)
				i2++;
		}

	}

	private void makeJLabel(int i2, int i) {
		final String[] coordinates = { " ", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "-" };
		GameLabelStrategy playerLabels;
		GameLabelStrategy enemyLabels;
		playerLabels = new GameLabelContext("P" + coordinates[i2] + i)
				.getLabel();
		enemyLabels = new GameLabelContext("E" + coordinates[i2] + i)
				.getLabel();

		if (playerLabels instanceof GameLabelBlank
				&& enemyLabels instanceof GameLabelBlank) {
			GameLabelBlank player = (GameLabelBlank) playerLabels;
			player.addMouseListener(new EnemyLabelClicked(player, this));
			GameLabelBlank enemy = (GameLabelBlank) enemyLabels;
			enemy.addMouseListener(new EnemyLabelClicked(enemy, this));
			playerBoard.put(player.getCoordinate(), player);
			enemyBoard.put(enemy.getCoordinate(), enemy);
		}

		playerPanel.add((JLabel) playerLabels);
		enemyPanel.add((JLabel) enemyLabels);
	}

	private void makeMidPanel() {
		boats.setLayout(new GridLayout(5, 0));
		boats.add(destroyer);
		boats.add(cruiser);
		boats.add(submarine);
		boats.add(battleship);
		boats.add(aircraftCarrier);

		statusText = new JTextArea();
		statusText.setBackground(Color.black.brighter());
		statusText.setForeground(Color.green.darker());
		statusText.setBounds(235, 178, 200, 100);
		mainPanel.add(statusText);

		exit.setBounds(193, 718, 121, 59);

		Font font = new Font("Verdana", Font.BOLD, 12);

		statusText.setFont(font);
		statusText.setEditable(false);

		exit.setIcon(new ImageIcon("Pictures//exitButton.gif"));
		exit.setBorderPainted(false);
		exit.setFocusable(false);
		exit.setContentAreaFilled(false);

		exit.addActionListener(new ExitGameOption(this));

		mainPanel.add(exit);
		typer = new TextTypingRobot(
				"Welcome to Battleship!Your ships have been placed on the board.You can now start the game",
				statusText);

	}

	@SuppressWarnings("deprecation")
	public void setNextMove(ArrayList<String> location) {
		this.location = location;

		typer.stop();
		if (output.hasEnded())
			typer = new TextTypingRobot(output.getWinner()
					+ " has won!\nCongratulations", statusText);
		else if (input.makeMove()) {

			if (location.get(0).charAt(0) == 'P') {
				if (playerBoard.get(location.get(0)).getColor()
						.equals(new Color(0, 255, 0, 0x50)))
					typer = new TextTypingRobot("Player firing at: "
							+ location.get(0).substring(1) + "\nHit!",
							statusText);
				else
					typer = new TextTypingRobot("Player firing at: "
							+ location.get(0).substring(1) + "\nMiss!",
							statusText);
			} else {
				if (enemyBoard.get(location.get(0)).getColor()
						.equals(new Color(0, 255, 0, 0x50)))
					typer = new TextTypingRobot("Enemy firing at: "
							+ location.get(0).substring(1) + "\nHit!",
							statusText);
				else
					typer = new TextTypingRobot("Enemy firing at: "
							+ location.get(0).substring(1) + "\nMiss!",
							statusText);
			}

		} else {
			typer = new TextTypingRobot("It's not your turn!", statusText);
		}
	}

	public void update() {
		output.update(playerBoard, enemyBoard);
	}

	public ArrayList<String> getNextMove() {
		return location;
	}

	public void setInputUnit(InputUnitBattleship input) {
		this.input = input;
	}

	public void setOutput(OutputUnitBattleship output) {
		this.output = output;
	}

}
