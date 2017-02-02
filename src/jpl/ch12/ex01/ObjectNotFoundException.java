package jpl.ch12.ex01;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends Exception {
	public final Object value;

	public ObjectNotFoundException(Object value) {
		super("Value \"" + value + "\" not found");
		this.value = value;
	}
}
