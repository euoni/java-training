package jpl.ch21.ex05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {
	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays.clone();
		int s = 0;
		for (final E[] array : arrays) {
			s += array.length;
		}
		size = s;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		int off = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (index < off + arrays[i].length) {
				return arrays[i][index - off];
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ABLListIterator();
	}

	private class ABLListIterator implements ListIterator<E> {
		private int off;
		private int array;
		private int pos;
		private int lastArray;
		private int lastPos;

		public ABLListIterator() {
			off = 0;
			array = 0;
			pos = -1;
			lastArray = -1;
			lastPos = -1;

			for (array = 0; array < arrays.length; array++) {
				if (arrays[array].length > 0)
					break;
			}
		}

		@Override
		public boolean hasNext() {
			return off + pos + 1 < size();
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			pos++;

			while (pos >= arrays[array].length) {
				off += arrays[array++].length;
				pos = 0;
				if (array >= arrays.length)
					break;
			}
			lastArray = array;
			lastPos = pos;
			return arrays[array][pos];
		}

		@Override
		public boolean hasPrevious() {
			return off + pos >= 0;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			lastArray = array;
			lastPos = pos;
			final E ret = arrays[array][pos--];

			while (pos < 0) {
				array--;
				if (array < 0) {
					off = 0;
					pos = -1;
					break;
				}
				off -= arrays[array].length;
				pos = arrays[array].length - 1;
			}
			return ret;
		}

		@Override
		public int nextIndex() {
			if (!hasNext())
				throw new NoSuchElementException();
			return off + pos + 1;
		}

		@Override
		public int previousIndex() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			return off + pos;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			if (lastArray == -1)
				throw new IllegalStateException();
			arrays[lastArray][lastPos] = e;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}
	}
}