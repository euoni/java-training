package java8.ch02.ex08;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zipper {
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				new ZipIter<>(Arrays.asList(first.iterator(), second.iterator())), Spliterator.IMMUTABLE), false);
	}

	private static class ZipIter<E> implements Iterator<E> {
		private final List<Iterator<E>> iters;
		private int i;

		public ZipIter(List<Iterator<E>> iters) {
			this.iters = iters;
			this.i = 0;
		}

		@Override
		public boolean hasNext() {
			return iters.stream().skip(i % iters.size()).allMatch(Iterator<E>::hasNext);
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return iters.get(i++ % iters.size()).next();
		}
	}
}
