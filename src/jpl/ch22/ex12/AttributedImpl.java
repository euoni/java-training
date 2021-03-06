package jpl.ch22.ex12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jpl.ch05.ex01.Attributed;

public class AttributedImpl implements Attributed {
	protected Map<String, Attr> attrTable = new HashMap<>();

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

}
