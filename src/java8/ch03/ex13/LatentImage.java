package java8.ch03.ex13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.UnaryOperator;

import java8.ch03.ex05.ColorTransformer;
import java8.ch03.ex11.ColorTransformerUtil;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
	private final Image in;
	private final Queue<Object> pendingOperations;

	public LatentImage(Image in) {
		this.in = in;
		pendingOperations = new LinkedList<>();
	}

	public LatentImage transform(ColorTransformer f) {
		pendingOperations.offer(f);
		return this;
	}

	public LatentImage transform(ColorFilter f) {
		pendingOperations.offer(f);
		return this;
	}

	public LatentImage transform(UnaryOperator<Color> f) {
		return transform(ColorTransformerUtil.pixelIndependent(f));
	}

	public Image toImage() {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		while (!pendingOperations.isEmpty()) {
			// process ColorTransformer
			final ArrayList<ColorTransformer> transformers = new ArrayList<>();
			while (pendingOperations.peek() instanceof ColorTransformer)
				transformers.add((ColorTransformer) pendingOperations.poll());
			if (!transformers.isEmpty()) {
				for (int x = 0; x < width; x++)
					for (int y = 0; y < height; y++) {
						Color c = in.getPixelReader().getColor(x, y);
						for (final ColorTransformer f : transformers)
							c = f.apply(x, y, c);
						out.getPixelWriter().setColor(x, y, c);
					}
			}

			// process ColorFilter
			if (pendingOperations.peek() instanceof ColorFilter) {
				final ColorFilter f = (ColorFilter) pendingOperations.poll();
				final WritableImage tmpOut = new WritableImage(width, height);
				for (int x = 0; x < width; x++)
					for (int y = 0; y < height; y++) {
						final Color c = f.apply(x, y, out.getPixelReader());
						tmpOut.getPixelWriter().setColor(x, y, c);
					}
				out = tmpOut;
			}
		}

		return out;
	}
}
