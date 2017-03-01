package jpl.ch16.ex03;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class ClassContents {
	public static void main(String[] args) {
		try {
			final Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c, c.getFields());
			printMembers(c, c.getDeclaredFields());
			printMembers(c, c.getConstructors());
			printMembers(c, c.getDeclaredConstructors());
			printMembers(c, c.getMethods());
			printMembers(c, c.getDeclaredMethods());
		} catch (final ClassNotFoundException e) {
			System.err.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Class<?> target, Member[] mems) {
		for (final Member m : mems) {
			if (m.getDeclaringClass() == Object.class)
				continue;
			if (m.getDeclaringClass() == target && Modifier.isPublic(m.getModifiers()))
				continue;
			final String decl = m.toString();

			// for testing
			if (decl.contains(".$jacoco"))
				continue;

			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String str, String toRemove) {
		return str.replaceAll(toRemove, "");
	}
}
