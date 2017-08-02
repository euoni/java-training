package java8.ch03.ex05;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Transformer {
	public static Image transform(Image in, ColorTransformer f) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}
}
