package jpl.ch01.ex15;

public class ArrayMutableLookup implements MutableLookup {
	private String[] names = new String[0];
	private Object[] values = new Object[0];

	@Override
	public Object find(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return values[i];
		}
		return null;
	}

	@Override
	public void add(String name, Object value) {
		final String[] new_names = new String[names.length + 1];
		final Object[] new_values = new Object[values.length + 1];

		for (int i = 0; i < names.length; i++) {
			new_names[i] = names[i];
			new_values[i] = values[i];
		}

		new_names[names.length] = name;
		new_values[values.length] = value;

		names = new_names;
		values = new_values;
	}

	@Override
	public boolean remove(String name) {
		Object found = null;
		int i;
		for (i = 0; i < names.length; ++i) {
			if (names[i].equals(name)) {
				found = values[i];
				break;
			}
		}
		if (found == null) {
			return false;
		}

		final String[] new_names = new String[names.length - 1];
		final Object[] new_values = new Object[values.length - 1];

		int k = 0;
		for (int j = 0; j < names.length; ++j) {
			if (j != i) {
				new_names[k] = names[j];
				new_values[k] = values[j];
				++k;
			}
		}

		names = new_names;
		values = new_values;

		return true;
	}

}
