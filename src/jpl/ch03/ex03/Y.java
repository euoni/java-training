package jpl.ch03.ex03;

public class Y extends X {
	private static final int yMask = 0xff00;

	@Override
	protected int getMask() {
		return super.getMask() | yMask;
	}
}
