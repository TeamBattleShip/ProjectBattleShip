package graphicaluserinterface.gamelabels;

import java.util.HashMap;

public class GameLabelContext {
	private HashMap<Character, GameLabelStrategy> container;
	private String strategy;
	private String name;

	public GameLabelContext(String strategy) {
		this.strategy = strategy;
		name = strategy.substring(1);
		container = new HashMap<Character, GameLabelStrategy>();
		container.put(' ', new GameLabelPrintNumbers(name));
		container.put('0', new GameLabelLetter(name));
		container.put('-', new GameLabelFilled(name));
	}

	public GameLabelStrategy getLabel() {

		if (name.substring(1).equals("11"))
			return new GameLabelFilled(strategy);

		if (checkIfContainsKey(name.charAt(0))) {
			return getStrategy(name.charAt(0));
		}

		if (checkIfContainsKey(name.charAt(1))) {
			return getStrategy(name.charAt(1));
		}

		return new GameLabelBlank(strategy);
	}

	private GameLabelStrategy getStrategy(char c) {
		return container.get(c);
	}

	private boolean checkIfContainsKey(char c) {
		return container.containsKey(c);
	}
}
