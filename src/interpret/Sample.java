package interpret;

import java.awt.Color;

@SuppressWarnings("unused")
public class Sample {
	private int privateInt;
	public double defaultDouble;
	protected boolean trueBoolean = true;
	public Color redColor = Color.RED;
	private final String constString = "CONST";
	public static short fiftyShort = 50;
	public String[][] stringArray = new String[][] { { "a" }, { "bb", "ccc" } };
	public int[] intArray = new int[] { 0, 1 };

	public void setBoolean(boolean b) {
		trueBoolean = b;
	}

	public String getString() {
		return "Sample Class";
	}
}
