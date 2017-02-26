package jpl.ch14.ex03;

import java.util.ArrayList;
import java.util.List;

public class Add {
	private int val;

	public synchronized void add() {
		val++;
		System.out.println(val);
	}

	public static void main(String[] args) throws InterruptedException {
		final Add my = new Add();

		final List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						my.add();
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
