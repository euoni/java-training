package jpl.ch14.ex02;

public class SingleLinkQueue<E> {
	class Cell {
		private Cell next;
		private E element;

		public Cell(E element) {
			this.element = element;
		}

		public Cell(E element, Cell next) {
			this.element = element;
			this.next = next;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Cell getNext() {
			return next;
		}

		public void setNext(Cell next) {
			this.next = next;
		}
	}

	protected Cell head;
	protected Cell tail;
	protected int size;

	public void add(E item) {
		final Cell cell = new Cell(item);
		if (tail == null)
			head = tail = cell;
		else {
			tail.setNext(cell);
			tail = cell;
		}
		size++;
	}

	public E remove() {
		if (head == null)
			return null;
		final Cell cell = head;
		head = head.getNext();
		if (head == null)
			tail = null;
		size--;
		return cell.getElement();
	}

	public int size() {
		return size;
	}
}
