package jpl.ch04.ex03;

public interface LinkedList {
	public Object getValue();

	void setValue(Object value);

	LinkedList getNext();

	void setNext(LinkedList next);
}
