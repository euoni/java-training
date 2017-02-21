package jpl.ch14.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SingleLinkQueueTest {
	@Test
	public void testCell() {
		final SingleLinkQueue<Integer> queue = new SingleLinkQueue<>();

		final SingleLinkQueue<Integer>.Cell cell0 = queue.new Cell(0);
		final SingleLinkQueue<Integer>.Cell cell1 = queue.new Cell(1, cell0);

		assertThat(cell1.getNext(), is(cell0));
	}

	@Test
	public void testCellSetElement() {
		final SingleLinkQueue<Integer> queue = new SingleLinkQueue<>();

		final SingleLinkQueue<Integer>.Cell cell = queue.new Cell(0);
		cell.setElement(1);

		assertThat(cell.getElement(), is(1));
	}

	@Test
	public void testAdd() {
		final SingleLinkQueue<Integer> queue = new SingleLinkQueue<>();
		queue.add(0);
		queue.add(1);

		assertThat(queue.remove(), is(0));
		assertThat(queue.remove(), is(1));
		assertThat(queue.remove(), is(nullValue()));
	}
}
