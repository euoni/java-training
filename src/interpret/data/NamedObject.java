package interpret.data;

import java.lang.reflect.Array;

public class NamedObject {
	private final String name;
	private final Object obj;

	public NamedObject(String name, Object obj) {
		this.name = name;
		this.obj = obj;
	}

	public NamedObject(NamedObject array, int index) {
		this.name = array.name + "[" + index + "]";
		this.obj = Array.get(array.obj, index);
	}

	public String getName() {
		return name;
	}

	public Object getObj() {
		return obj;
	}

	@Override
	public String toString() {
		String type;
		if (obj == null) {
			type = "null";
		} else if (obj.getClass().isArray()) {
			final int length = Array.getLength(obj);
			type = obj.getClass().getComponentType().getName() + "[" + length + "]";
		} else
			type = obj.getClass().getName();
		type = type.replace("java.lang.", "");

		return name + " (" + type + ")";
	}
}
