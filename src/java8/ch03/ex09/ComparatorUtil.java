package java8.ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class ComparatorUtil {
	public static Comparator<Object> lexicographicComparator(String... fieldNames) {
		return (o1, o2) -> {
			for (final String filedName : fieldNames) {
				// get field
				Field field1, field2;
				try {
					field1 = o1.getClass().getField(filedName);
					field2 = o2.getClass().getField(filedName);
				} catch (NoSuchFieldException | SecurityException e) {
					throw new RuntimeException(e);
				}

				// get field value
				Object v1, v2;
				try {
					v1 = field1.get(o1);
					v2 = field2.get(o2);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}

				// compare
				@SuppressWarnings("unchecked")
				final int ret = ((Comparable<Object>) v1).compareTo(v2);
				if (ret != 0)
					return ret;

			}
			return 0;
		};
	}
}
