package jpl.ch21.ex04;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ShortListStringsTest {
	private List<String> data;

	@Before
	public void setUp() throws Exception {
		data = new LinkedList<>();
		data.add("1");
		data.add("12");
		data.add("123");
	}

	@Test
	public void testHasNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		assertThat(iter.hasNext(), is(true));
		// test multiple call before next()
		assertThat(iter.hasNext(), is(true));

		iter.next();
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.hasNext(), is(true));

		iter.next();
		assertThat(iter.hasNext(), is(false));
		assertThat(iter.hasNext(), is(false));
	}

	@Test(expected = NoSuchElementException.class)
	public void testNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		assertThat(iter.next(), is("1"));
		assertThat(iter.next(), is("12"));
		iter.next();
	}

	@Test
	public void testHasPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		assertThat(iter.hasPrevious(), is(true));
		// test multiple call before previous()
		assertThat(iter.hasPrevious(), is(true));

		iter.previous();
		assertThat(iter.hasPrevious(), is(true));
		assertThat(iter.hasPrevious(), is(true));

		iter.previous();
		assertThat(iter.hasPrevious(), is(false));
		assertThat(iter.hasPrevious(), is(false));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		assertThat(iter.previous(), is("12"));
		assertThat(iter.previous(), is("1"));
		iter.previous();
	}

	@Test(expected = NoSuchElementException.class)
	public void testNextIndex() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		assertThat(iter.nextIndex(), is(0));
		assertThat(iter.nextIndex(), is(0));
		iter.next();

		assertThat(iter.nextIndex(), is(1));
		assertThat(iter.nextIndex(), is(1));
		iter.next();

		iter.nextIndex();
	}

	@Test(expected = NoSuchElementException.class)
	public void testPreviousIndex() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		assertThat(iter.previousIndex(), is(1));
		assertThat(iter.previousIndex(), is(1));
		iter.previous();

		assertThat(iter.previousIndex(), is(0));
		assertThat(iter.previousIndex(), is(0));
		iter.previous();

		iter.previousIndex();
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveBeforeNextOrPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);
		iter.remove();
	}

	@Test
	public void testRemoveNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.remove();
		assertThat(data, contains("12", "123"));
	}

	@Test
	public void testRemovePrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.remove();
		assertThat(data, contains("1", "123"));
	}

	@Test
	public void testRemoveAfterHasNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.hasNext();
		iter.remove();

		// iter should remove the item returned by last next() call
		assertThat(data, contains("12", "123"));
	}

	@Test
	public void testRemoveAfterHasNext2() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.next();
		iter.hasNext();
		iter.remove();

		// iter should remove the item returned by last next() call
		assertThat(data, contains("1", "123"));
	}

	@Test
	public void testRemoveAfterHasPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.hasPrevious();
		iter.remove();

		// iter should remove the item returned by last previous() call
		assertThat(data, contains("1", "123"));
	}

	@Test
	public void testRemoveAfterHasPrevious2() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.previous();
		iter.hasPrevious();
		iter.remove();

		// iter should remove the item returned by last previous() call
		assertThat(data, contains("12", "123"));
	}

	@Test(expected = IllegalStateException.class)
	public void testSetBeforeNextOrPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);
		iter.set("");
	}

	@Test
	public void testSetNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.set("");
		assertThat(data, contains("", "12", "123"));
	}

	@Test
	public void testSetPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.set("");
		assertThat(data, contains("1", "", "123"));
	}

	@Test
	public void testSetAfterHasNext() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.hasNext();
		iter.set("");

		// iter should set the item returned by last next() call
		assertThat(data, contains("", "12", "123"));
	}

	@Test
	public void testSetAfterHasNext2() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);

		iter.next();
		iter.next();
		iter.hasNext();
		iter.set("");

		// iter should set the item returned by last next() call
		assertThat(data, contains("1", "", "123"));
	}

	@Test
	public void testSetAfterHasPrevious() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.hasPrevious();
		iter.set("");

		// iter should set the item returned by last previous() call
		assertThat(data, contains("1", "", "123"));
	}

	@Test
	public void testSetAfterHasPrevious2() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(3), 2);

		iter.previous();
		iter.previous();
		iter.hasPrevious();
		iter.set("");

		// iter should set the item returned by last previous() call
		assertThat(data, contains("", "12", "123"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		final ShortListStrings iter = new ShortListStrings(data.listIterator(), 2);
		iter.add("");
	}
}
