package jpl.ch16.ex11;

import jpl.ch16.ex11.Game.HeadsOrTails;

public class TailPlayer implements Player {
	@Override
	public void play(Game game) {
		for (int i = 0; i < 5; i++) {
			game.buttle(HeadsOrTails.TAIL);
		}
	}
}
