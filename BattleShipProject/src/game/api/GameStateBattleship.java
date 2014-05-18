package game.api;

import game.api.placeboats.PlaceBoatContext;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;
import game.io.InputListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStateBattleship implements GameState, InputListener,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5553374248527611351L;
	private List<Player> players;
	private Integer turnCounter = 0;
	private Board board;
	private String message = "";
	private RuleChecker ruleChecker;
	private int playerHits = 0;
	private int enemyHits = 0;
	private int bugG = 0;

	public GameStateBattleship() {
		ArrayList<BoardLocation> locations = new ArrayList<BoardLocation>();
		final String[] coordinates = { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J" };
		makeBoardLocations(locations, coordinates);

		board = new Board(locations);

		players = new ArrayList<Player>();
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new GamePiece("S"));
		pieces.add(new GamePiece("B"));

		players.add(new Player("Player", pieces));
		players.add(new Player("Enemy", pieces));
		ruleChecker = new RuleChecker();
		placeRandomBoats(coordinates);

	}

	public GameStateBattleship(int test) {
		ArrayList<BoardLocation> locations = new ArrayList<BoardLocation>();
		final String[] coordinates = { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J" };
		makeBoardLocations(locations, coordinates);

		board = new Board(locations);

		players = new ArrayList<Player>();
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
		pieces.add(new GamePiece("S"));
		pieces.add(new GamePiece("B"));

		players.add(new Player("Player", pieces));
		players.add(new Player("Enemy", pieces));
		ruleChecker = new RuleChecker();
	}

	private void makeBoardLocations(ArrayList<BoardLocation> locations,
			final String[] coordinates) {
		for (String s : coordinates)
			for (int i = 1; i < 11; i++) {
				locations.add(new BoardLocation("E" + s + i));
			}

		for (String s : coordinates)
			for (int i = 1; i < 11; i++) {
				locations.add(new BoardLocation("P" + s + i));
			}
	}

	private void placeRandomBoats(final String[] coordinates) {
		ArrayList<Integer> ships = new ArrayList<>();

		ships.add(2);
		ships.add(3);
		ships.add(3);
		ships.add(4);
		ships.add(5);
		Random rand = new Random();

		for (Integer i : ships) {

			while (!placeShips(i, 1 + rand.nextInt(10),
					rand.nextInt(coordinates.length), rand.nextBoolean()))
				;
			while (!placeShips(i, 1 + rand.nextInt(10),
					rand.nextInt(coordinates.length), rand.nextBoolean()))
				;
		}

	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {

		return players.get(((turnCounter - 1) + 2) % 2);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Player getPlayerInTurn() {
		return players.get(turnCounter % 2);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Player getWinner() {
		if (hasEnded()) {
			return getLastPlayer();
		}
		return null;
	}

	@Override
	public Boolean hasEnded() {
		return ruleChecker.isGameFinishied(enemyHits, playerHits);
	}

	@Override
	public Boolean proposeMove(Move move) {
		bugG = (bugG + 1) % 2;

		if (bugG % 2 == 0) {
			if (ruleChecker.isValidMove(move, this)) {
				if (move.getDestinations().size() == 1)
					for (BoardLocation bl : board.getLocations())
						if (bl.getId().equals(
								move.getDestinations().get(0).getId())) {
							if (bl.getPiece() != null) {
								move = new Move(move.getPlayer(),
										new GamePiece("H"),
										move.getDestinations());
								if (move.getPlayer().getName().equals("Player"))
									playerHits++;
								else
									enemyHits++;

							} else
								move = new Move(move.getPlayer(),
										new GamePiece("M"),
										move.getDestinations());
						}

				move.execute();
				turnCounter++;
				message = "bajs";
				return true;
			}
			message = "Wrong move dude..!";
			return false;
		}
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public DieRollFactory getDieRollFactory() {

		return null;
	}

	@Override
	public void inputOccured(Move move) {
		System.out.println("-------");
		proposeMove(move);
	}

	private boolean placeShips(int shipSize, int xIndex, int yIndex,
			boolean vertical) {
		Move m = new Move(getPlayerInTurn(), getPlayerInTurn().getPieces().get(
				0), new PlaceBoatContext(vertical, xIndex, yIndex, shipSize)
				.getPlaceBoatStrategy().getLocations(this));

		return proposeMove(m);
	}

}
