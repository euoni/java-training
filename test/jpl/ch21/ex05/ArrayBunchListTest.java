package jpl.ch21.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ArrayBunchListTest {
	private final Integer[][] data = { {}, { 1, 2 }, { 3 }, {} };

	@Test
	public void testSize() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		assertThat(list.size(), is(3));
	}

	@Test
	public void testGetInt() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
		assertThat(list.get(2), is(3));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetIntException1() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		list.get(-1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetIntException2() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		list.get(3);
	}

	@Test(expected = NoSuchElementException.class)
	public void testHasNextAndNext() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(1));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(2));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(3));
		assertThat(iter.hasNext(), is(false));
		assertThat(iter.hasNext(), is(false));
		iter.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testHasPreviousAndPrevious() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();

		iter.next();
		iter.next();
		iter.next();

		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.previous(), is(3));
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.previous(), is(2));
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.previous(), is(1));
		assertThat(iter.hasPrevious(), is(false));
		assertThat(iter.hasPrevious(), is(false));
		iter.previous();
	}

	@Test(expected = NoSuchElementException.class)
	public void testNextIndex() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		assertThat(iter.nextIndex(), is(0));
		iter.next();
		iter.hasNext();
		assertThat(iter.nextIndex(), is(1));
		iter.next();
		iter.hasNext();
		assertThat(iter.nextIndex(), is(2));
		iter.next();
		iter.hasNext();
		iter.nextIndex();
	}

	@Test(expected = NoSuchElementException.class)
	public void testPrevoiusIndex() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();

		iter.next();
		iter.next();
		iter.next();

		assertThat(iter.previousIndex(), is(2));
		iter.previous();
		iter.hasPrevious();
		assertThat(iter.previousIndex(), is(1));
		iter.previous();
		iter.hasPrevious();
		assertThat(iter.previousIndex(), is(0));
		iter.previous();
		iter.hasPrevious();
		iter.previousIndex();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testSetBeforeNextOrPrevious() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.set(0);
	}

	@Test
	public void testSetAfterNext1() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.next();
		iter.set(0);
		assertThat(data[1][0], is(0));
	}

	@Test
	public void testSetAfterNext2() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.next();
		iter.set(0);
		assertThat(data[2][0], is(0));
	}

	@Test
	public void testSetAfterPrevious1() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.next();
		iter.previous();
		iter.set(0);
		assertThat(data[1][0], is(0));
	}

	@Test
	public void testSetAfterPrevious2() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.next();
		iter.previous();
		iter.set(0);
		assertThat(data[2][0], is(0));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		final ArrayBunchList<Integer> list = new ArrayBunchList<>(data);
		final ListIterator<Integer> iter = list.listIterator();
		iter.add(0);
	}
}
