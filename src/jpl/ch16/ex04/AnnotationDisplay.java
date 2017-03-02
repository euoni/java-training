package jpl.ch16.ex04;

import java.lang.annotation.Annotation;

public class AnnotationDisplay {
	public static void main(String[] args) {
		try {
			final Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			for (final Annotation ann : c.getAnnotations()) {
				System.out.println("  " + ann);
			}
		} catch (final ClassNotFoundException e) {
			System.err.println("unknown class: " + args[0]);
		}
	}
}
