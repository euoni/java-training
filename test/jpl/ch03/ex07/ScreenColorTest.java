package jpl.ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ScreenColorTest {
	private final static ScreenColor transparent = new ScreenColor("transparent");
	private final static ScreenColor red = new ScreenColor("red");
	private final static ScreenColor green = new ScreenColor("green");
	private final static ScreenColor blue = new ScreenColor("blue");
	private final static ScreenColor black = new ScreenColor("black");

	@Test(expected = UnsupportedOperationException.class)
	public void testScreenColorException() {
		new ScreenColor(0);
	}

	@Test
	public void testToString() {
		assertThat(transparent.toString(), is("#00000000"));
		assertThat(red.toString(), is("#ff0000ff"));
		assertThat(green.toString(), is("#00ff00ff"));
		assertThat(blue.toString(), is("#0000ffff"));
		assertThat(black.toString(), is("#000000ff"));
	}

	@Test
	public void testGetR() {
		assertThat(transparent.getR(), is(0.));
		assertThat(red.getR(), is(1.));
		assertThat(green.getR(), is(0.));
		assertThat(blue.getR(), is(0.));
		assertThat(black.getR(), is(0.));
	}

	@Test
	public void testGetG() {
		assertThat(transparent.getG(), is(0.));
		assertThat(red.getG(), is(0.));
		assertThat(green.getG(), is(1.));
		assertThat(blue.getG(), is(0.));
		assertThat(black.getG(), is(0.));
	}

	@Test
	public void testGetB() {
		assertThat(transparent.getB(), is(0.));
		assertThat(red.getB(), is(0.));
		assertThat(green.getB(), is(0.));
		assertThat(blue.getB(), is(1.));
		assertThat(black.getB(), is(0.));
	}

	@Test
	public void testGetA() {
		assertThat(transparent.getA(), is(0.));
		assertThat(red.getA(), is(1.));
		assertThat(green.getA(), is(1.));
		assertThat(blue.getA(), is(1.));
		assertThat(black.getA(), is(1.));
	}

	@Test
	public void testHashCode() {
		final ScreenColor transparent2 = new ScreenColor("transparent");
		final ScreenColor red2 = new ScreenColor("red");
		final ScreenColor green2 = new ScreenColor("green");
		final ScreenColor blue2 = new ScreenColor("blue");
		final ScreenColor black2 = new ScreenColor("black");

		assertThat(transparent.hashCode(), is(transparent2.hashCode()));
		assertThat(red.hashCode(), is(red2.hashCode()));
		assertThat(green.hashCode(), is(green2.hashCode()));
		assertThat(blue.hashCode(), is(blue2.hashCode()));
		assertThat(black.hashCode(), is(black2.hashCode()));
	}

	@Test
	public void testEquals() {
		final ScreenColor red2 = new ScreenColor("red");

		assertThat(red, is(red));

		assertThat(red, not(is((ScreenColor) null)));

		assertThat(red, not(is(0)));

		assertThat(red, is(red2));
		assertThat(red2, is(red));

		assertThat(transparent, not(is(red)));
		assertThat(transparent, not(is(green)));
		assertThat(transparent, not(is(blue)));
		assertThat(transparent, not(is(black)));

		assertThat(red, not(is(transparent)));
		assertThat(red, not(is(green)));
		assertThat(red, not(is(blue)));
		assertThat(red, not(is(black)));

		assertThat(green, not(is(transparent)));
		assertThat(green, not(is(red)));
		assertThat(green, not(is(blue)));
		assertThat(green, not(is(black)));

		assertThat(blue, not(is(transparent)));
		assertThat(blue, not(is(red)));
		assertThat(blue, not(is(green)));
		assertThat(blue, not(is(black)));
	}
}
