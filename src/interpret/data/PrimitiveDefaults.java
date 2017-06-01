package interpret.data;

public class PrimitiveDefaults {
	private static boolean DEFAULT_BOOLEAN;
	private static byte DEFAULT_BYTE;
	private static short DEFAULT_SHORT;
	private static int DEFAULT_INT;
	private static long DEFAULT_LONG;
	private static float DEFAULT_FLOAT;
	private static double DEFAULT_DOUBLE;

	public static Object getDefaultValue(Class<?> klass) {
		if (klass == boolean.class) {
			return DEFAULT_BOOLEAN;
		} else if (klass == byte.class) {
			return DEFAULT_BYTE;
		} else if (klass == short.class) {
			return DEFAULT_SHORT;
		} else if (klass == int.class) {
			return DEFAULT_INT;
		} else if (klass == long.class) {
			return DEFAULT_LONG;
		} else if (klass == float.class) {
			return DEFAULT_FLOAT;
		} else if (klass == double.class) {
			return DEFAULT_DOUBLE;
		} else {
			throw new IllegalArgumentException("Class type " + klass + " not supported");
		}
	}

	public static Class<?> wrap(Class<?> klass) {
		if (klass == boolean.class) {
			return Boolean.class;
		} else if (klass == byte.class) {
			return Byte.class;
		} else if (klass == short.class) {
			return Short.class;
		} else if (klass == int.class) {
			return Integer.class;
		} else if (klass == long.class) {
			return Long.class;
		} else if (klass == float.class) {
			return Float.class;
		} else if (klass == double.class) {
			return Double.class;
		}

		return klass;
	}
}
