package java8.ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import java8.ch03.ex05.ColorTransformer;
import java8.ch03.ex11.ColorTransformerUtil;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ParallelLatentImage {
	private final Color[][] in;
	private final List<ColorTransformer> pendingOperations = new ArrayList<>();

	public ParallelLatentImage(Image image) {
		final int width = (int) image.getWidth();
		final int height = (int) image.getHeight();
		in = new Color[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				in[y][x] = image.getPixelReader().getColor(x, y);
			}
		}
	}

	public ParallelLatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	public ParallelLatentImage transform(UnaryOperator<Color> f) {
		return transform(ColorTransformerUtil.pixelIndependent(f));
	}

	public Image toImage() {

		final int n = Runtime.getRuntime().availableProcessors();
		final int height = in.length;
		final int width = in[0].length;
		final Color[][] out = new Color[height][width];
		try {
			final ExecutorService pool = Executors.newCachedThreadPool();
			for (int i = 0; i < n; i++) {
				final int fromY = i * height / n;
				final int toY = (i + 1) * height / n;

				pool.submit(() -> {
					for (int x = 0; x < width; x++)
						for (int y = fromY; y < toY; y++) {
							Color c = in[y][x];
							for (final ColorTransformer f : pendingOperations)
								c = f.apply(x, y, c);
							out[y][x] = c;
						}
				});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (final InterruptedException ex) {
			throw new RuntimeException(ex);
		}

		final WritableImage image = new WritableImage(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				image.getPixelWriter().setColor(x, y, out[y][x]);
			}
		}
		return image;
	}
}
