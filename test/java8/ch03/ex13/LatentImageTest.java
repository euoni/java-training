package java8.ch03.ex13;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java8.ch03.ex08.Border;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class LatentImageTest {
	@Test
	public void testBlur() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));
		final int w = (int) in.getWidth();
		final int h = (int) in.getHeight();

		final Image out = new LatentImage(in).transform(c -> c.deriveColor(0, 1, 1.2, 1))
				.transform(Border.get(10, Color.GRAY, w, h)).transform((int x, int y, PixelReader reader) -> {
					final ColorVector v = new ColorVector();
					int count = 0;

					if ((y - 1) >= 0) {
						v.add(reader.getColor(x, y - 1));
						count++;
					}
					if ((x + 1) < w && (y - 1) >= 0) {
						v.add(reader.getColor(x + 1, y - 1));
						count++;
					}
					if ((x + 1) < w) {
						v.add(reader.getColor(x + 1, y));
						count++;
					}
					if ((x + 1) < w && (y + 1) < h) {
						v.add(reader.getColor(x + 1, y + 1));
						count++;
					}
					if ((y + 1) < h) {
						v.add(reader.getColor(x, y + 1));
						count++;
					}
					if ((x - 1) > 0 && (y + 1) < h) {
						v.add(reader.getColor(x - 1, y + 1));
						count++;
					}
					if ((x - 1) > 0) {
						v.add(reader.getColor(x - 1, y));
						count++;
					}
					if ((x - 1) > 0 && (y - 1) > 0) {
						v.add(reader.getColor(x - 1, y - 1));
						count++;
					}
					{
						v.add(reader.getColor(x, y));
						count++;
					}

					v.devide(count);

					return v.getColor();
				}).toImage();

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_brighter_bordered_blur.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}

	private static class ColorVector {
		private double r = 0, g = 0, b = 0;

		public void add(Color c) {
			r += c.getRed();
			g += c.getGreen();
			b += c.getBlue();
		}

		public void devide(double k) {
			r /= k;
			g /= k;
			b /= k;
		}

		public Color getColor() {
			return new Color(r, g, b, 1.);
		}
	}
}
