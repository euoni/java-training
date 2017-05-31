package jpl.ch21.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class StackTest {
	@Test
	public void testPushPop() {
		final Stack<Integer> stack = new Stack<>();

		stack.push(1);
		stack.push(2);
		stack.push(3);

		assertThat(stack.pop(), is(3));
		assertThat(stack.pop(), is(2));
		assertThat(stack.pop(), is(1));
	}

	@Test(expected = EmptyStackException.class)
	public void testPopEmpty() {
		final Stack<Integer> stack = new Stack<>();
		stack.pop();
	}

	@Test
	public void testPeek() {
		final Stack<Integer> stack = new Stack<>();

		stack.push(1);
		assertThat(stack.peek(), is(1));
		assertThat(stack.pop(), is(1));
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekEmpty() {
		final Stack<Integer> stack = new Stack<>();
		stack.peek();
	}

	@Test
	public void testSearch() {
		final Stack<Integer> stack = new Stack<>();

		stack.push(1);
		stack.push(2);
		stack.push(3);

		assertThat(stack.search(3), is(1));
		assertThat(stack.search(2), is(2));
		assertThat(stack.search(1), is(3));
		assertThat(stack.search(0), is(-1));
	}
}
