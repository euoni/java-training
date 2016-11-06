package jpl.ch03.ex03;

public class X {
	private static final int xMask = 0x00ff;
	private final int fullMask;

	/**
	 * マスクを初期化する．
	 * 初期化中に{@link #getMask()}を呼ぶ．
	 */
	public X() {
		fullMask = getMask();
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	protected int getMask() {
		return xMask;
	}
}
