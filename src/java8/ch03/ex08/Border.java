package java8.ch03.ex08;

import java8.ch03.ex05.ColorTransformer;
import javafx.scene.paint.Color;

public class Border {
	public static ColorTransformer get(int borderWidth, Color borderColor, int width, int height) {
		return (x, y, c) -> {
			return (x < borderWidth || (width - x) <= borderWidth || y < borderWidth || (height - y) <= borderWidth)
					? borderColor : c;
		};
	}
}
