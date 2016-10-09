package jpl.ch01.ex08;

public class Point {

	public double x, y;

	public static Point origin = new Point();

	public void clear() {
		x = 0.0;
		y = 0.0;
	}

	public double distance(final Point that) {
		final double xdiff = x - that.x;
		final double ydiff = y - that.y;

		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void move(final Point that) {
		move(that.x, that.y);
	}
}
