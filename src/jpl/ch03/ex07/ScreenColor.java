package jpl.ch03.ex07;

public class ScreenColor {
	private final double r;
	private final double g;
	private final double b;
	private final double a;

	public ScreenColor(Object value) {
		if (value == "transparent") {
			r = 0.;
			g = 0.;
			b = 0.;
			a = 0.;
		} else if (value == "red") {
			r = 1.;
			g = 0.;
			b = 0.;
			a = 1.;
		} else if (value == "green") {
			r = 0.;
			g = 1.;
			b = 0.;
			a = 1.;
		} else if (value == "blue") {
			r = 0.;
			g = 0.;
			b = 1.;
			a = 1.;
		} else if (value == "black") {
			r = 0.;
			g = 0.;
			b = 0.;
			a = 1.;
		} else
			throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.format("#%02x%02x%02x%02x", (int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
	}

	public double getR() {
		return r;
	}

	public double getG() {
		return g;
	}

	public double getB() {
		return b;
	}

	public double getA() {
		return a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ScreenColor other = (ScreenColor) obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}
}
