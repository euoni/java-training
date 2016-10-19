package jpl.ch02.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testLinkedListObject() {
		final String value = "v";
		final LinkedList item = new LinkedList(value);

		assertThat(item.value, is(value));
		assertThat(item.next, nullValue());
	}

	@Test
	public void testLinkedListObjectLinkedList() {
		final String value1 = "v1";
		final LinkedList item1 = new LinkedList(value1);

		final String value2 = "v2";
		final LinkedList item2 = new LinkedList(value2, item1);

		assertThat(item2.value, is(value2));
		assertThat(item2.next, is(item1));
	}

	@Test
	public void testSetNextLinkedListArray() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);
		final LinkedList item3 = new LinkedList(null);

		assertThat(item1.next, nullValue());

		item1.setNext(item2, item3);

		assertThat(item1.next, is(item2));
		assertThat(item2.next, is(item3));
	}
}
