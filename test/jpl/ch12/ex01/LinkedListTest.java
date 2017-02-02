package jpl.ch12.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedListTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

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
	public void testFind() throws ObjectNotFoundException {
		final LinkedList<String> item1 = new LinkedList<>("ITEM1");
		final LinkedList<String> item2 = new LinkedList<>("ITEM2");

		item1.setNext(item2);
		assertThat(item1.find("ITEM1"), is(item1));
		assertThat(item1.find("ITEM2"), is(item2));
	}

	@Test
	public void testFindException() throws ObjectNotFoundException {
		final LinkedList<String> item1 = new LinkedList<>("ITEM1");

		thrown.expect(ObjectNotFoundException.class);
		thrown.expectMessage("Value \"0\" not found");
		item1.find(0);
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
