package jpl.ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<E> {
	private final ArrayList<E> data;

	public Stack() {
		data = new ArrayList<>();
	}

	public void push(E e) {
		data.add(e);
	}

	public E pop() {
		if (data.isEmpty())
			throw new EmptyStackException();
		return data.remove(data.size() - 1);
	}

	public E peek() {
		if (data.isEmpty())
			throw new EmptyStackException();
		return data.get(data.size() - 1);
	}

	public int search(E e) {
		final int i = data.lastIndexOf(e);
		return i == -1 ? -1 : data.size() - i;
	}
}
