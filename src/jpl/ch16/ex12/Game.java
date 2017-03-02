package jpl.ch16.ex12;

import java.util.Arrays;

import jpl.ch16.ex11.Player;

public class Game extends jpl.ch16.ex11.Game {
	public static void main(String[] args) {
		players.addAll(Arrays.asList(args));

		String name;
		while ((name = getNextPlayer()) != null) {
			try {
				final PlayerLoader loader = new PlayerLoader();
				final Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				final Player player = classOf.newInstance();
				final Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (final Exception e) {
				reportException(name, e);
			}
		}
	}
}
