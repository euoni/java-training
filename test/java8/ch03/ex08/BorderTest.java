package java8.ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java8.ch03.ex05.Transformer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BorderTest {
	@Test
	public void testCtor() {
		new Border();
	}

	@Test
	public void testGet10Red() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final Image out = Transformer.transform(in,
				Border.get(10, Color.RED, (int) in.getWidth(), (int) in.getHeight()));

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_10red.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}

	@Test
	public void testGet20Green() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final Image out = Transformer.transform(in,
				Border.get(20, Color.LIME, (int) in.getWidth(), (int) in.getHeight()));

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_20green.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}
}
