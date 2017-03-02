package jpl.ch16.ex11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Game {
	public enum HeadsOrTails {
		HEAD, TAIL;
	}

	private static Queue<String> players = new ArrayDeque<>();
	private int score;
	private HeadsOrTails pre = HeadsOrTails.HEAD;

	public void buttle(HeadsOrTails choice) {
		final HeadsOrTails coin = pre == HeadsOrTails.HEAD ? HeadsOrTails.TAIL : HeadsOrTails.HEAD;

		if (coin == choice)
			score++;
		else
			score--;

		pre = coin;
	}

	private void reportScore(String name) {
		System.out.println(name + "'s score: " + score);
	}

	private static void reportException(String name, Exception e) {
		System.err.println(name + "threw Exception");
		e.printStackTrace();
	}

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

	private static String getNextPlayer() {
		return players.poll();
	}
}
