package jpl.ch02.ex11;

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
	public void testToString() {
		final LinkedList item1 = new LinkedList("ITEM1");
		final LinkedList item2 = new LinkedList("ITEM2", item1);

		assertThat(item1.toString(), is("LinkedList [value=ITEM1, next=null]"));
		assertThat(item2.toString(), is("LinkedList [value=ITEM2, next=LinkedList [value=ITEM1, next=null]]"));
	}
}
