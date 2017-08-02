package java8.ch03.ex11;

import java.util.function.UnaryOperator;

import java8.ch03.ex05.ColorTransformer;
import javafx.scene.paint.Color;

public class ColorTransformerUtil {
	public static ColorTransformer compose(ColorTransformer t1, ColorTransformer t2) {
		return (x, y, c) -> t2.apply(x, y, t1.apply(x, y, c));
	}

	public static ColorTransformer pixelIndependent(UnaryOperator<Color> t) {
		return (x, y, c) -> t.apply(c);
	}
}
