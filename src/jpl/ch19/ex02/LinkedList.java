package jpl.ch19.ex02;

/**
 * A <code>LinkedList</code> object represents a node of linked list.
 *
 * @author euoni
 */
public class LinkedList {
	/** The value of node. */
	private Object value;
	/** A node next to this. */
	private LinkedList next;

	/**
	 * Creates a single node with the given value.
	 */
	public LinkedList(Object value) {
		this.value = value;
	}

	/**
	 * Returns this node's value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets this node's value.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Returns the node next to this.
	 */
	public LinkedList getNext() {
		return next;
	}

	/**
	 * Sets a chain of nodes starting from this.
	 * The chain meets {@code items[i].next == items[i+1]}.
	 */
	public void setNext(LinkedList... items) {
		LinkedList prev = this;
		for (final LinkedList item : items) {
			prev.next = item;
			prev = item;
		}
	}

	/**
	 * Returns the length of list from this to the end node.
	 */
	public int getCount() {
		int count = 0;
		LinkedList p = this;
		while (p != null) {
			p = p.getNext();
			++count;
		}
		return count;
	}

	/**
	 * Returns a string of the form
	 * "<code>LinkedList [value=VALUE, next=NEXT_NODE]</code>".
	 */
	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
