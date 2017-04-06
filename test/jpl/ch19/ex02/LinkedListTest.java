package jpl.ch19.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testLinkedListObject() {
		final String value = "v";
		final LinkedList item = new LinkedList(value);

		assertThat(item.getValue(), is(value));
		assertThat(item.getNext(), nullValue());
	}

	@Test
	public void testValue() {
		final String beforeValue = "";
		final String afterValue = "v";
		final LinkedList item = new LinkedList(beforeValue);

		assertThat(item.getValue(), is(beforeValue));
		item.setValue(afterValue);
		assertThat(item.getValue(), is(afterValue));
	}

	@Test
	public void testGetNext() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);

		item1.setNext(item2);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), nullValue());
	}

	@Test
	public void testSetNextLinkedListArray() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);
		final LinkedList item3 = new LinkedList(null);

		item1.setNext(item2, item3);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), is(item3));
		assertThat(item3.getNext(), nullValue());
	}

	@Test
	public void testCount() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);
		item1.setNext(item2);

		assertThat(item1.getCount(), is(2));
		assertThat(item2.getCount(), is(1));
	}

	@Test
	public void testToString() {
		final LinkedList item1 = new LinkedList("ITEM1");
		final LinkedList item2 = new LinkedList("ITEM2");
		item1.setNext(item2);

		assertThat(item1.toString(), is("LinkedList [value=ITEM1, next=LinkedList [value=ITEM2, next=null]]"));
		assertThat(item2.toString(), is("LinkedList [value=ITEM2, next=null]"));
	}
}
