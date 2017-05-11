package jpl.ch22.ex04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import jpl.ch05.ex01.Attributed;

public class ObservableAttributed extends Observable implements Attributed {
	private final Map<String, Attr> data = new HashMap<>();

	@Override
	public void add(Attr newAttr) {
		data.put(newAttr.getName(), newAttr);

		setChanged();
		notifyObservers(new Event(newAttr, "add"));
	}

	@Override
	public Attr find(String attrName) {
		return data.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		final Attr old = data.remove(attrName);

		setChanged();
		notifyObservers(new Event(old, "remove"));

		return old;
	}

	@Override
	public Iterator<Attr> attrs() {
		return data.values().iterator();
	}

	public class Event {
		Attr attr;
		String type;

		public Event(Attr attr, String event) {
			this.attr = attr;
			this.type = event;
		}

		public Attr getAttr() {
			return attr;
		}

		public String getType() {
			return type;
		}
	}
}
