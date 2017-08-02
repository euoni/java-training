package java8.ch03.ex14;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class LatentImageTest {
	@Test
	public void testGray() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final Image out = new LatentImage(in).transform(Color::grayscale).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_gray.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}

	@Test
	public void testFlip() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));
		final int w = (int) in.getWidth();

		final Image out = new LatentImage(in).transform((x, y, r) -> r.getColor(w - (x + 1), y)).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_flip.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}

	@Test
	public void testFlipGray() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));
		final int w = (int) in.getWidth();

		final Image out = new LatentImage(in).transform((x, y, r) -> r.getColor(w - (x + 1), y))
				.transform(Color::grayscale).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_flip_gray.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}

	@Test
	public void testGrayFlip() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));
		final int w = (int) in.getWidth();

		final Image out = new LatentImage(in).transform(Color::grayscale)
				.transform((x, y, r) -> r.getColor(w - (x + 1), y)).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_flip_gray.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}
}
