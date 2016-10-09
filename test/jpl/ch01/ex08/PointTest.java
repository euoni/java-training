package jpl.ch01.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testClear() {
		final Point p = new Point();
		p.x = 1;
		p.y = 2;

		p.clear();

		assertEquals(0, p.x, 0);
		assertEquals(0, p.y, 0);
	}

	@Test
	public void testDistance() {
		final Point p1 = Point.origin;

		final Point p2 = new Point();
		p2.x = 1;
		p2.y = 0;

		final Point p3 = new Point();
		p3.x = 0;
		p3.y = 1;

		assertEquals(1, p1.distance(p2), 0);
		assertEquals(1, p1.distance(p3), 0);
		assertEquals(Math.sqrt(2), p2.distance(p3), 0);
	}

	@Test
	public void testMoveDoubleDouble() {
		final Point p = new Point();
		p.move(1, 2);

		assertEquals(1, p.x, 0);
		assertEquals(2, p.y, 0);
	}

	@Test
	public void testMovePoint() {
		final Point target = new Point();
		target.x = 1;
		target.y = 2;

		final Point p = new Point();
		p.move(target);

		assertEquals(target.x, p.x, 0);
		assertEquals(target.y, p.y, 0);
	}

}
