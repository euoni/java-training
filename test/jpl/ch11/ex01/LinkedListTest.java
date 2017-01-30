package jpl.ch11.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testLinkedList() {
		final String value = "v";
		final LinkedList<String> item = new LinkedList<>(value);

		assertThat(item.getValue(), is(value));
		assertThat(item.getNext(), nullValue());
	}

	@Test
	public void testValue() {
		final String beforeValue = "";
		final String afterValue = "v";
		final LinkedList<String> item = new LinkedList<>(beforeValue);

		assertThat(item.getValue(), is(beforeValue));
		item.setValue(afterValue);
		assertThat(item.getValue(), is(afterValue));
	}

	@Test
	public void testGetNext() {
		final LinkedList<String> item1 = new LinkedList<>(null);
		final LinkedList<String> item2 = new LinkedList<>(null);

		item1.setNext(item2);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), nullValue());
	}

	@Test
	public void testSetNextLinkedListArray() {
		final LinkedList<String> item1 = new LinkedList<>(null);
		final LinkedList<String> item2 = new LinkedList<>(null);

		item1.setNext(item2);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), nullValue());
	}

	@Test
	public void testToStringString() {
		final LinkedList<String> item1 = new LinkedList<>("ITEM1");
		final LinkedList<String> item2 = new LinkedList<>("ITEM2");
		item1.setNext(item2);

		assertThat(item1.toString(), is("LinkedList [value=ITEM1, next=LinkedList [value=ITEM2, next=null]]"));
		assertThat(item2.toString(), is("LinkedList [value=ITEM2, next=null]"));
	}

	@Test
	public void testToStringInteger() {
		final LinkedList<Integer> item1 = new LinkedList<>(1);
		final LinkedList<Integer> item2 = new LinkedList<>(2);
		item1.setNext(item2);

		assertThat(item1.toString(), is("LinkedList [value=1, next=LinkedList [value=2, next=null]]"));
		assertThat(item2.toString(), is("LinkedList [value=2, next=null]"));
	}
}
