package java8.ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.image.Image;

public class TransformerTest {
	@Test
	public void testCtor() {
		new Transformer();
	}

	@Test
	public void testTransformBrighter() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final Image out = Transformer.transform(in, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_brighter1.2.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}
}
