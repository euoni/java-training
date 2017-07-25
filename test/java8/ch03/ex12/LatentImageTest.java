package java8.ch03.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java8.ch03.ex08.Border;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class LatentImageTest {
	@Test
	public void test() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final Image out = new LatentImage(in).transform(c -> c.deriveColor(0, 1, 1.2, 1))
				.transform(Border.get(10, Color.GRAY, (int) in.getWidth(), (int) in.getHeight())).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_brighter_bordered.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}
}
