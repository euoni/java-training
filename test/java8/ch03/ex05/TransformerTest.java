package java8.ch03.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TransformerTest {
	@Test
	public void testCtor() {
		new Transformer();
	}

	@Test
	public void testTransformBordered() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final int border = 10;
		final Image out = Transformer.transform(in, (x, y, c) -> {
			return (x < border || (in.getWidth() - x) <= border || y < border || (in.getHeight() - y) <= border)
					? Color.BLACK : c;
		});

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_bordered.png"));
		assertThat(equals(out, expect), is(true));
	}

	public static boolean equals(Image img1, Image img2) {
		if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight())
			return false;

		for (int y = 0; y < img1.getHeight(); y++)
			for (int x = 0; x < img1.getWidth(); x++)
				if (!img1.getPixelReader().getColor(x, y).equals(img2.getPixelReader().getColor(x, y)))
					return false;

		return true;
	}
}
