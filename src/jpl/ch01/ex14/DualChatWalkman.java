package jpl.ch01.ex14;

public class DualChatWalkman extends DualWalkman {
	public enum Listener {
		ONE, TWO
	}

	public void chat(Listener listener, String message) {
		switch (listener) {
		case ONE:
			System.out.println("1: " + message);
			break;
		case TWO:
			System.out.println("2: " + message);
			break;
		default:
			break;
		}
	}
}
