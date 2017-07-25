package java8.ch03.ex13;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorFilter {
	Color apply(int x, int y, PixelReader r);
}
