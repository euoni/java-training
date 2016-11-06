package jpl.ch03.ex02;

public class Y extends X {
	protected int yMask = 0xff00;

	{
		System.out.printf("Y Init\txMask=%04x\tyMask=%04x\tfullMask=%04x%n", xMask, yMask, fullMask);
	}

	public Y() {
		fullMask |= yMask;
		System.out.printf("Y Const\txMask=%04x\tyMask=%04x\tfullMask=%04x%n", xMask, yMask, fullMask);
	}

	public static void main(String[] args) {
		new Y();
	}
}
