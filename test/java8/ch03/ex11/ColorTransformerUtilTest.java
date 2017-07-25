package java8.ch03.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java8.ch03.ex05.ColorTransformer;
import java8.ch03.ex05.Transformer;
import java8.ch03.ex08.Border;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ColorTransformerUtilTest {
	@Test
	public void testCtor() {
		new ColorTransformerUtil();
	}

	@Test
	public void testCompose() {
		final ColorTransformer t1 = (x, y, c) -> new Color(c.getBlue(), c.getGreen(), c.getRed(), 1.);
		final ColorTransformer t2 = (x, y, c) -> new Color(c.getRed(), c.getGreen(), c.getBlue(), 0.);

		assertThat(ColorTransformerUtil.compose(t1, t2).apply(0, 0, Color.RED), is(new Color(0., 0., 1., 0.)));
		assertThat(ColorTransformerUtil.compose(t2, t1).apply(0, 0, Color.RED), is(new Color(0., 0., 1., 1.)));
	}

	@Test
	public void testPixelIndependent() {
		final ColorTransformer t = ColorTransformerUtil.pixelIndependent(c -> Color.BLUE);

		assertThat(t.apply(0, 0, Color.RED), is(Color.BLUE));
		assertThat(t.apply(0, 1, Color.RED), is(Color.BLUE));
		assertThat(t.apply(1, 0, Color.RED), is(Color.BLUE));
		assertThat(t.apply(1, 1, Color.RED), is(Color.BLUE));
	}

	@Test
	public void testCombo() {
		final Image in = new Image(getClass().getResourceAsStream("../queen-mary.png"));

		final ColorTransformer brighter = ColorTransformerUtil.pixelIndependent(c -> c.deriveColor(0, 1, 1.2, 1));
		final ColorTransformer border = Border.get(10, Color.GRAY, (int) in.getWidth(), (int) in.getHeight());
		final ColorTransformer t = ColorTransformerUtil.compose(brighter, border);
		final Image out = Transformer.transform(in, t);

		final Image expect = new Image(getClass().getResourceAsStream("queen-mary_brighter_bordered.png"));
		assertThat(java8.ch03.ex05.TransformerTest.equals(out, expect), is(true));
	}
}
