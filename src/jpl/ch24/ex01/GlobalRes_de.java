package jpl.ch24.ex01;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class GlobalRes_de extends ResourceBundle {
	@Override
	protected Object handleGetObject(String key) {
		if ("hello".equals(key))
			return "Hallo";
		else if ("goodbye".equals(key))
			return "Tschüs";
		return null;
	}

	@Override
	public Enumeration<String> getKeys() {
		return java.util.Collections.enumeration(Arrays.asList("hello", "goodbye"));
	}
}
