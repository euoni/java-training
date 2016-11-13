package jpl.ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ColorAttrTest {
	@Test
	public void testSetValueObject() {
		final ColorAttr attr = new ColorAttr("color", "red");
		attr.setValue("blue");

		assertThat(attr.getValue(), is("blue"));
		assertThat(attr.getMyColor().toString(), is("#0000ffff"));
	}

	@Test
	public void testColorAttrStringObject() {
		final ColorAttr attr = new ColorAttr("color", "red");

		assertThat(attr.getValue(), is("red"));
		assertThat(attr.getMyColor().toString(), is("#ff0000ff"));
	}

	@Test
	public void testColorAttrStringObjectNull() {
		final ColorAttr attr = new ColorAttr("color", (Object) null);

		assertThat(attr.getValue(), nullValue());
		assertThat(attr.getMyColor(), nullValue());
	}

	@Test
	public void testColorAttrString() {
		final ColorAttr attr = new ColorAttr("color");

		assertThat(attr.getValue(), is("transparent"));
		assertThat(attr.getMyColor().toString(), is("#00000000"));
	}

	@Test
	public void testColorAttrStringScreenColor() {
		final ScreenColor color = new ScreenColor("green");
		final ColorAttr attr = new ColorAttr("color", color);

		assertThat(attr.getValue(), is("#00ff00ff"));
		assertThat(attr.getMyColor(), is(color));
	}

	@Test
	public void testSetValueScreenColor() {
		final ColorAttr attr = new ColorAttr("color", "red");
		final ScreenColor color = new ScreenColor("blue");
		attr.setValue(color);

		assertThat(attr.getValue(), is("#0000ffff"));
		assertThat(attr.getMyColor(), is(color));
	}

	@Test
	public void testHashCode() {
		final ColorAttr x1 = new ColorAttr("x", "red");
		final ColorAttr x2 = new ColorAttr("x", "red");
		final ColorAttr y1 = new ColorAttr("y", (Object) null);
		final ColorAttr y2 = new ColorAttr("y", (Object) null);

		assertThat(x1.hashCode(), is(x2.hashCode()));
		assertThat(y1.hashCode(), is(y2.hashCode()));
	}

	@Test
	public void testEquals() {
		final ColorAttr x1 = new ColorAttr("x", "red");
		final ColorAttr x2 = new ColorAttr("x", "red");
		final ColorAttr y1 = new ColorAttr("y", (Object) null);
		final ColorAttr y2 = new ColorAttr("y", (Object) null);

		assertThat(x1, is(x1));

		assertThat(x1, is(x2));
		assertThat(x2, is(x1));

		assertThat(y1, is(y2));
		assertThat(y2, is(y1));

		assertThat(x1, not(is(y1)));
		assertThat(y1, not(is(x1)));
	}
}
