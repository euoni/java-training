package jpl.ch07.ex02;

public class LiteralAssignment {
	public char c;
	public byte b;
	public short s;
	public int i;
	public long l;
	public float f;
	public double d;

	public void setChar() {
		final char val = 'A';
		c = val;
		b = val;
		s = val;
		i = val;
		l = val;
		f = val;
		d = val;
		System.out.printf("set char: val = '%c'%n", val);
	}

	public void setShort() {
		final short val = 0x1234;
		c = val;
		b = (byte) val;
		s = val;
		i = val;
		l = val;
		f = val;
		d = val;
		System.out.printf("set int: val = %d(0x%1$x)%n", val);
	}

	public void setInt() {
		final int val = 0x12345678;
		c = (char) val;
		b = (byte) val;
		s = (short) val;
		i = val;
		l = val;
		f = val;
		d = val;
		System.out.printf("set int: val = %d(0x%1$x)%n", val);
	}

	public void setLong() {
		final long val = 0x1234567890ABCDEFL;
		c = (char) val;
		b = (byte) val;
		s = (short) val;
		i = (int) val;
		l = val;
		f = val;
		d = val;
		System.out.printf("set long: val = %d(0x%1$x)%n", val);
	}

	public void setFloat() {
		final float val = 0.123456789f;
		c = (char) val;
		b = (byte) val;
		s = (short) val;
		i = (int) val;
		l = (long) val;
		f = val;
		d = val;
		System.out.printf("set float: val = %f%n", val);
	}

	public void setDouble() {
		final double val = 0.123456789d;
		c = (char) val;
		b = (byte) val;
		s = (short) val;
		i = (int) val;
		l = (long) val;
		f = (float) val;
		d = val;
		System.out.printf("set double: val = %f%n", val);
	}

	public void print() {
		System.out.printf("char: %d(0x%1$x)%n", (short) c);
		System.out.printf("byte: %d(0x%1$x)%n", b);
		System.out.printf("short: %d(0x%1$x)%n", s);
		System.out.printf("int: %d(0x%1$x)%n", i);
		System.out.printf("long: %d(0x%1$x)%n", l);
		System.out.printf("float: %.9f%n", f);
		System.out.printf("double: %.9f%n", d);
		System.out.println("");
	}

	public static void main(String[] args) {
		final LiteralAssignment la = new LiteralAssignment();

		la.setChar();
		la.print();

		la.setShort();
		la.print();

		la.setInt();
		la.print();

		la.setLong();
		la.print();

		la.setFloat();
		la.print();

		la.setDouble();
		la.print();
	}
}
