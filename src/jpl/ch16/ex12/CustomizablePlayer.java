package jpl.ch16.ex12;

import java.io.IOException;
import java.io.InputStream;

import jpl.ch16.ex11.Game;
import jpl.ch16.ex11.Game.HeadsOrTails;
import jpl.ch16.ex11.Player;

public class CustomizablePlayer implements Player {
	@Override
	public void play(Game game) {
		final InputStream stream = getClass().getResourceAsStream("choice");
		int choice;
		try {
			choice = stream.read();
		} catch (final IOException e) {
			choice = 0;
		} catch (final NullPointerException e) {
			choice = 0;
		}

		for (int i = 0; i < 5; i++) {
			game.buttle(choice == 0 ? HeadsOrTails.HEAD : HeadsOrTails.TAIL);
		}
	}
}
