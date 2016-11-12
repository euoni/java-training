package jpl.ch03.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex10.LinkedList;

public class LinkedListTest {
	@Test
	public void testClone() throws CloneNotSupportedException {
		final String val = "val";
		final LinkedList item1 = new LinkedList(val);
		final LinkedList item2 = new LinkedList(null);
		item1.setNext(item2);

		final LinkedList cloned1 = item1.clone();

		assertThat(cloned1, not(is(item1)));
		assertThat(cloned1.getValue(), is(val));
		assertThat(cloned1.getNext(), is(item2));
	}
}
