package jpl.ch02.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void test() {
		final LinkedList first = new LinkedList();
		first.value = "first value";

		final LinkedList second = new LinkedList();
		second.value = "second value";

		first.next = second;

		assertThat(first.next.value, is("second value"));
	}
}
