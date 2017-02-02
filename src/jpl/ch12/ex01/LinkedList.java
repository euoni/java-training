package jpl.ch12.ex01;

public class LinkedList<E> {
	private E value;
	private LinkedList<E> next;

	public LinkedList(E value) {
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	public LinkedList<E> find(Object value) throws ObjectNotFoundException {
		for (LinkedList<E> item = this; item != null; item = item.next) {
			if (item.value.equals(value))
				return item;
		}

		// 例外で通知すると、呼び出し側はnullチェックをする必要がなく、処理の流れがわかりやすくなる
		throw new ObjectNotFoundException(value);
	}

	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
