package java8.ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import java8.ch03.ex05.ColorTransformer;
import java8.ch03.ex11.ColorTransformerUtil;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
	private final Image in;
	private final List<ColorTransformer> pendingOperations;

	public LatentImage(Image in) {
		this.in = in;
		pendingOperations = new ArrayList<>();
	}

	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		return transform(ColorTransformerUtil.pixelIndependent(f));
	}

	public Image toImage() {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (final ColorTransformer f : pendingOperations)
					c = f.apply(x, y, c);
				out.getPixelWriter().setColor(x, y, c);
			}
		return out;
	}
}
