package jpl.ch14.ex05;

import java.util.ArrayList;
import java.util.List;

public class Add {
	private static int val;

	public static void main(String[] args) throws InterruptedException {
		final List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						synchronized (Add.class) {
							val++;
							System.out.println(val);
						}
					}
				}
			});
			thread.start();
			list.add(thread);
		}

		for (final Thread thread : list) {
			thread.join();
		}
	}
}
