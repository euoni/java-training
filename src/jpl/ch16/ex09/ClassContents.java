package jpl.ch16.ex09;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Comparator;

public class ClassContents {
	private static String[] types = { "class", "interface", "enum", "annotation" };

	public static void main(String[] args) {
		try {
			final Class<?> c = Class.forName(args[0]);

			// @...
			printAnnotations(c, "");

			// modifiers
			printModifiers(c.getModifiers(), " ");

			// class, interface, ...
			printClassType(c);
			System.out.print(" ");

			// name
			System.out.print(c.getCanonicalName());

			// <...>
			printTypeParameters(c.getTypeParameters(), "");

			// extends ...
			if (c.getGenericSuperclass() != null && c.getGenericSuperclass() != Object.class) {
				System.out.print(" extends ");
				printType(c.getGenericSuperclass());
			}

			// implements ...
			final Type[] interfaces = c.getGenericInterfaces();
			if (interfaces.length > 0) {
				System.out.print(" implements ");
				for (final Type type : interfaces) {
					printType(type);
					System.out.print(", ");
				}
				System.out.print("\b\b");
			}

			// body
			System.out.println(" {");

			printFields(c.getDeclaredFields());
			printExecutable(c.getDeclaredConstructors());
			printExecutable(c.getDeclaredMethods());

			System.out.println("}");
		} catch (final ClassNotFoundException e) {
			System.err.println("unknown class: " + args[0]);
		}
	}

	private static void printModifiers(int mod, String post) {
		if (mod != 0)
			System.out.print(Modifier.toString(mod) + post);
	}

	private static void printType(Type t) {
		if (t instanceof ParameterizedType) {
			final ParameterizedType pt = (ParameterizedType) t;

			final String name = ((Class<?>) pt.getRawType()).getCanonicalName();
			System.out.print(strip(name));

			printTypes(pt.getActualTypeArguments(), "<", "\b\b>");
		} else if (t instanceof TypeVariable<?>) {
			final TypeVariable<?> tv = (TypeVariable<?>) t;

			System.out.print(tv.getName());

			final Type[] bounds = tv.getBounds();
			if (bounds.length != 1 || bounds[0] != Object.class) {
				printTypes(bounds, " extends ", "\b\b");
			}
		} else if (t instanceof GenericArrayType) {
			final GenericArrayType gat = (GenericArrayType) t;
			printType(gat.getGenericComponentType());
			System.out.print("[]");
		} else if (t instanceof WildcardType) {
			final WildcardType wt = (WildcardType) t;
			System.out.print("?");

			printTypes(wt.getLowerBounds(), " super ", "\b\b");

			final Type[] bounds = wt.getUpperBounds();
			if (bounds.length != 1 || bounds[0] != Object.class) {
				printTypes(bounds, " extends ", "\b\b");
			}
		} else {
			final String name = ((Class<?>) t).getCanonicalName();
			System.out.print(strip(name));
		}
	}

	private static void printTypes(Type[] types, String pre, String post) {
		if (types.length > 0) {
			System.out.print(pre);
			for (final Type type : types) {
				printType(type);
				System.out.print(", ");
			}
			System.out.print(post);
		}
	}

	private static void printClassType(Class<?> c) {
		final int idx = c.isAnnotation() ? 3 : c.isEnum() ? 2 : c.isInterface() ? 1 : 0;
		System.out.print(types[idx]);
	}

	private static void printExecutable(Executable[] executable) {
		Arrays.sort(executable, new Comparator<Executable>() {
			@Override
			public int compare(Executable o1, Executable o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		for (final Executable e : executable) {
			// for test
			if (e.getName().equals("$jacocoInit"))
				continue;

			// @...
			printAnnotations(e, "  ");
			System.out.print("  ");

			// modifiers
			printModifiers(e.getModifiers(), " ");

			// <...>
			printTypeParameters(e.getTypeParameters(), " ");

			// return
			if (e instanceof Method) {
				printType(((Method) e).getGenericReturnType());
				System.out.print(" ");
			}

			// name
			System.out.print(e.getName());

			// params
			if (e.getParameterCount() == 0) {
				System.out.print("()");
			} else {
				final Annotation[][] annotations = e.getParameterAnnotations();
				final Type[] parameterTypes = e.getGenericParameterTypes();

				System.out.print("(");
				for (int i = 0; i < parameterTypes.length; i++) {
					for (final Annotation ann : annotations[i]) {
						System.out.print(strip(ann.toString()));
						System.out.print(" ");
					}

					printType(parameterTypes[i]);
					System.out.print(", ");
				}
				System.out.print("\b\b");

				// var args
				if (e.isVarArgs())
					System.out.print("\b\b...)");
				else
					System.out.print(")");
			}

			// throws
			printTypes(e.getGenericExceptionTypes(), " throws ", "\b\b");

			// tail
			System.out.println(" {}");
			System.out.println();
		}
	}

	private static void printTypeParameters(TypeVariable<?>[] typeParameters, String post) {
		if (typeParameters.length > 0) {
			System.out.print("<");
			for (final TypeVariable<?> typeVariable : typeParameters) {
				printType(typeVariable);
				System.out.print(", ");
			}
			System.out.print("\b\b>" + post);
		}
	}

	private static void printFields(Field[] fields) {
		for (final Field f : fields) {
			// for test
			if (f.getName().equals("$jacocoData"))
				continue;

			// @...
			printAnnotations(f, "  ");
			System.out.print("  ");

			// modifiers
			printModifiers(f.getModifiers(), " ");

			// type
			printType(f.getGenericType());
			System.out.print(" ");

			// name
			System.out.print(strip(f.getName()));

			// tail
			System.out.println(";");
			System.out.println();
		}
	}

	private static void printAnnotations(AnnotatedElement c, String pre) {
		for (final Annotation ann : c.getDeclaredAnnotations()) {
			System.out.println(pre + strip(ann.toString()));
		}
	}

	private static String strip(String str) {
		return str.replaceAll("java.lang.", "");
	}
}
