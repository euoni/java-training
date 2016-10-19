package jpl.ch02.ex08;

public class LinkedList extends jpl.ch02.ex02.LinkedList {
	public LinkedList(Object value) {
		this(value, null);
	}

	public LinkedList(Object value, LinkedList next) {
		this.value = value;
		this.next = next;
	}
}
