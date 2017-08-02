package java8.ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import java8.ch03.ex13.ColorFilter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class LatentImage {
	private final Image in;
	private final List<ColorFilter> pendingOperations;

	public LatentImage(Image in) {
		this.in = in;
		pendingOperations = new ArrayList<>();
	}

	public LatentImage transform(ColorFilter f) {
		pendingOperations.add(f);
		return this;
	}

	public LatentImage transform(UnaryOperator<Color> f) {
		return transform((x, y, r) -> f.apply(r.getColor(x, y)));
	}

	public Image toImage() {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);

		PixelReader reader = in.getPixelReader();
		for (final ColorFilter f : pendingOperations) {
			reader = new CachedReader(reader, f, width, height);
		}
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, reader.getColor(x, y));

		return out;
	}

	private static class CachedReader implements PixelReader {
		private final PixelReader last;
		private final ColorFilter f;
		private final WritableImage cache;
		private final boolean[][] hit;

		public CachedReader(PixelReader last, ColorFilter f, int width, int height) {
			this.last = last;
			this.f = f;
			// FIXME: 元画像と同じ大きさの画像をキャッシュ用に確保するためキャッシュのヒット率が低いとメモリの無駄が多くなる
			this.cache = new WritableImage(width, height);
			this.hit = new boolean[height][width];
		}

		@Override
		public PixelFormat<?> getPixelFormat() {
			return cache.getPixelReader().getPixelFormat();
		}

		@Override
		public int getArgb(int x, int y) {
			checkCache(x, y);
			return cache.getPixelReader().getArgb(x, y);
		}

		@Override
		public Color getColor(int x, int y) {
			checkCache(x, y);
			return cache.getPixelReader().getColor(x, y);
		}

		@Override
		public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat,
				T buffer, int scanlineStride) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer,
				int offset, int scanlineStride) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer,
				int offset, int scanlineStride) {
			throw new UnsupportedOperationException();
		}

		private void checkCache(int x, int y) {
			if (hit[y][x])
				return;
			cache.getPixelWriter().setColor(x, y, f.apply(x, y, last));
			hit[y][x] = true;
		}
	}
}
