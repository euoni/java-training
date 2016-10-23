package jpl.ch02.ex16;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testCount() {
		final LinkedList item1 = new LinkedList(null);
		final LinkedList item2 = new LinkedList(null);
		item1.setNext(item2);

		assertThat(item1.getCount(), is(2));
		assertThat(item2.getCount(), is(1));
	}
}
