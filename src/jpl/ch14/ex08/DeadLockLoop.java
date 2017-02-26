package jpl.ch14.ex08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeadLockLoop {
	public static void main(String[] args) {
		final int count = Integer.parseInt(args[0]);
		int deadlock = 0;

		for (int i = 0; i < count; i++) {
			System.out.println("# Test " + i);

			// start
			Friendly.main(null);

			// get threads
			final Thread[] tarray = new Thread[Thread.activeCount()];
			Thread.enumerate(tarray);
			final List<Thread> list = Arrays.stream(tarray)
					.filter(t -> !t.isInterrupted() && (t.getName().equals("Thread1") || t.getName().equals("Thread2")))
					.collect(Collectors.toList());

			// join
			list.forEach(t -> {
				try {
					t.join(10);
				} catch (final InterruptedException e) {
					;
				}
			});

			// check
			if (list.stream().anyMatch(t -> t.isAlive())) {
				list.forEach(t -> t.interrupt());
				deadlock++;
			}
		}

		System.out.println("Deadlock: " + deadlock + " / " + count);
		System.exit(0);
	}
}
