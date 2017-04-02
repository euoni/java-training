package jpl.ch17.ex01;

public class InspectMemory {
	public static void main(String[] args) {
		System.out.println("# initial");
		dispMemory();

		System.out.println("# make garbage");
		final Object[] objs = new Object[1000000];
		for (int i = 0; i < objs.length; i++)
			objs[i] = new Object();
		dispMemory();

		System.out.println("# collect garbage");
		for (int i = 0; i < objs.length; i++)
			objs[i] = null;
		System.gc();
		dispMemory();
	}

	private static void dispMemory() {
		System.out.println("free memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "MiB");
	}
}
