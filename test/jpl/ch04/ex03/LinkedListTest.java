package jpl.ch04.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testLinkedListObject() {
		final String value = "v";
		final LinkedList item = new LinkedListImpl(value);

		assertThat(item.getValue(), is(value));
		assertThat(item.getNext(), nullValue());
	}

	@Test
	public void testValue() {
		final String beforeValue = "";
		final String afterValue = "v";
		final LinkedList item = new LinkedListImpl(beforeValue);

		assertThat(item.getValue(), is(beforeValue));
		item.setValue(afterValue);
		assertThat(item.getValue(), is(afterValue));
	}

	@Test
	public void testGetNext() {
		final LinkedList item1 = new LinkedListImpl(null);
		final LinkedList item2 = new LinkedListImpl(null);

		item1.setNext(item2);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), nullValue());
	}

	@Test
	public void testSetNextLinkedListArray() {
		final LinkedList item1 = new LinkedListImpl(null);
		final LinkedList item2 = new LinkedListImpl(null);

		item1.setNext(item2);
		assertThat(item1.getNext(), is(item2));
		assertThat(item2.getNext(), nullValue());
	}

	@Test
	public void testToString() {
		final LinkedList item1 = new LinkedListImpl("ITEM1");
		final LinkedList item2 = new LinkedListImpl("ITEM2");
		item1.setNext(item2);

		assertThat(item1.toString(), is("LinkedListImpl [value=ITEM1, next=LinkedListImpl [value=ITEM2, next=null]]"));
		assertThat(item2.toString(), is("LinkedListImpl [value=ITEM2, next=null]"));
	}
}
