package java8.ch01.ex09;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class Collection2Test {
	@Test
	public void testForEachIf() {
		final Collection2<Integer> list = new Collection2Impl<>(Arrays.asList(0, 1, 2, 3, 4, 5));

		final List<Integer> ret = new ArrayList<>();
		list.forEachIf(ret::add, i -> i % 2 == 0);
		assertThat(ret, contains(0, 2, 4));
	}

	private static class Collection2Impl<E> implements Collection2<E> {
		private final Collection<E> data;

		public Collection2Impl(Collection<E> data) {
			this.data = data;
		}

		@Override
		public int size() {
			return data.size();
		}

		@Override
		public boolean isEmpty() {
			return data.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			return data.contains(o);
		}

		@Override
		public Iterator<E> iterator() {
			return data.iterator();
		}

		@Override
		public Object[] toArray() {
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return data.toArray(a);
		}

		@Override
		public boolean add(E e) {
			return data.add(e);
		}

		@Override
		public boolean remove(Object o) {
			return data.remove(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return containsAll(c);
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return data.addAll(c);
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return data.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return data.retainAll(c);
		}

		@Override
		public void clear() {
			data.clear();
		}
	}
}
