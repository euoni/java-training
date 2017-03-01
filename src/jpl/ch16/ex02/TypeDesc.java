package jpl.ch16.ex02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {

	public static void main(String[] args) {
		final TypeDesc desc = new TypeDesc();
		for (final String name : args) {
			try {
				final Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (final ClassNotFoundException e) {
				System.err.println(e);
			}
		}
	}

	private final java.io.PrintStream out = System.out;

	private static String[] basic = { "class", "interface", "enum", "annotation" },
			supercl = { "extends", "implements" }, iFace = { null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		if (type == null)
			return;

		Class<?> cls = null;
		if (type instanceof Class<?>)
			cls = (Class<?>) type;
		else if (type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		else
			throw new Error("Unexpected non-class type");

		for (int i = 0; i < depth; i++)
			out.print(" ");
		final int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2 : cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		final TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (final TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.print("\b\b>");
		}

		final Class<?> enclosingClass = cls.getEnclosingClass();
		if (enclosingClass != null) {
			out.print(" in " + cls.getCanonicalName());
		} else
			out.println();

		final Type[] interfaces = cls.getGenericInterfaces();
		for (final Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}

		if (cls.getGenericSuperclass() != Object.class)
			printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}
}
