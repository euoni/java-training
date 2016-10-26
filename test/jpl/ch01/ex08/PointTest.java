package jpl.ch01.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {
	@Test
	public void testClear() {
		final Point p = new Point();
		p.x = 1;
		p.y = 2;

		p.clear();

		assertThat(p.x, is(0.));
		assertThat(p.y, is(0.));
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

		assertThat(p1.distance(p2), is(1.));
		assertThat(p1.distance(p3), is(1.));
		assertThat(p2.distance(p3), is(Math.sqrt(2.)));
	}

	@Test
	public void testMoveDoubleDouble() {
		final Point p = new Point();
		p.move(1, 2);

		assertThat(p.x, is(1.));
		assertThat(p.y, is(2.));
	}

	@Test
	public void testMovePoint() {
		final Point target = new Point();
		target.x = 1.;
		target.y = 2.;

		final Point p = new Point();
		p.move(target);

		assertThat(p.x, is(target.x));
		assertThat(p.y, is(target.y));
	}
}
