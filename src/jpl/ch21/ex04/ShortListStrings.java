package jpl.ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortListStrings implements ListIterator<String> {
	private final ListIterator<String> strings;
	private String prevShort;
	private String nextShort;
	private final int maxLen;
	private int nextIndex;
	private int prevIndex;
	private int lastIndex = -1;

	public ShortListStrings(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		prevShort = null;
		nextShort = null;
	}

	@Override
	public boolean hasNext() {
		if (nextShort != null)
			return true;
		while (strings.hasNext()) {
			nextIndex = strings.nextIndex();
			nextShort = strings.next();
			if (nextShort.length() <= maxLen) {
				return true;
			}
		}
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();
		lastIndex = nextIndex;
		final String n = nextShort;
		nextShort = null;
		return n;
	}

	@Override
	public boolean hasPrevious() {
		if (prevShort != null)
			return true;
		while (strings.hasPrevious()) {
			prevIndex = strings.previousIndex();
			prevShort = strings.previous();
			if (prevShort.length() <= maxLen) {
				return true;
			}
		}
		prevShort = null;
		return false;
	}

	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious())
			throw new NoSuchElementException();
		lastIndex = prevIndex;
		final String n = prevShort;
		prevShort = null;
		return n;
	}

	@Override
	public int nextIndex() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();
		return nextIndex;
	}

	@Override
	public int previousIndex() {
		if (prevShort == null && !hasPrevious())
			throw new NoSuchElementException();
		return prevIndex;
	}

	@Override
	public void remove() {
		if (lastIndex == -1)
			throw new IllegalStateException();

		rollBack();

		strings.remove();
	}

	@Override
	public void set(String e) {
		if (lastIndex == -1)
			throw new IllegalStateException();

		rollBack();

		strings.set(e);
	}

	@Override
	public void add(String e) {
		throw new UnsupportedOperationException();
	}

	private void rollBack() {
		if (lastIndex <= strings.previousIndex())
			while (lastIndex <= strings.previousIndex())
				strings.previous();
		else
			while (lastIndex >= strings.nextIndex())
				strings.next();
	}
}
