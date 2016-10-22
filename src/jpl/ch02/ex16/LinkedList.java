package jpl.ch02.ex16;

public class LinkedList extends jpl.ch02.ex14.LinkedList {
	public LinkedList(Object value) {
		super(value);
	}

	public int getCount() {
		int count = 0;
		jpl.ch02.ex14.LinkedList p = this;
		while (p != null) {
			p = p.getNext();
			++count;
		}
		return count;
	}
}
