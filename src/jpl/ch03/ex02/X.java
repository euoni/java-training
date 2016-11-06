package jpl.ch03.ex02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;

	{
		System.out.printf("X Init\txMask=%04x\tfullMask=%04x%n", xMask, fullMask);
	}

	public X() {
		fullMask = xMask;
		System.out.printf("X Const\txMask=%04x\tfullMask=%04x%n", xMask, fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}
}
