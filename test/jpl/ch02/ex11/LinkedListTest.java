package jpl.ch02.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import mockit.Deencapsulation;

public class LinkedListTest {

	@Test
	public void testLinkedListObject() {
		final String value = "v";
		final LinkedList item = new LinkedList(value);

		assertThat(Deencapsulation.getField(item, "value"), is(value));
		assertThat(Deencapsulation.getField(item, "next"), nullValue());
	}

	@Test
	public void testLinkedListObjectLinkedList() {
		final String value1 = "v1";
		final LinkedList item1 = new LinkedList(value1);

		final String value2 = "v2";
		final LinkedList item2 = new LinkedList(value2, item1);

		assertThat(Deencapsulation.getField(item2, "value"), is(value2));
		assertThat(Deencapsulation.getField(item2, "next"), is(item1));
	}

	@Test
	public void testGetValue() {
		final String value = "v";
		final LinkedList item = new LinkedList(value);

		assertThat(item.getValue(), is(value));
	}

	@Test
	public void testSetValue() {
		final String beforeValue = "";
		final String afterValue = "v";
		final LinkedList item = new LinkedList(beforeValue);

		assertThat(Deencapsulation.getField(item, "value"), is(beforeValue));

		item.setValue(afterValue);

		assertThat(Deencapsulation.getField(item, "value"), is(afterValue));
	}

	@Test
	public void testGetNext() {
		final String value1 = "v1";
		final LinkedList item1 = new LinkedList(value1);

		final String value2 = "v2";
		final LinkedList item2 = new LinkedList(value2, item1);

		assertThat(item1.getNext(), nullValue());
		assertThat(item2.getNext(), is(item1));
	}

	@Test
	public void testSetNext() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);

		assertThat(Deencapsulation.getField(item2, "next"), nullValue());

		item2.setNext(item1);

		assertThat(Deencapsulation.getField(item2, "next"), is(item1));
	}

	@Test
	public void testToString() {
		final LinkedList item1 = new LinkedList("ITEM1");
		final LinkedList item2 = new LinkedList("ITEM2", item1);

		assertThat(item1.toString(), is("LinkedList [value=ITEM1, next=null]"));
		assertThat(item2.toString(), is("LinkedList [value=ITEM2, next=LinkedList [value=ITEM1, next=null]]"));

	}
}
