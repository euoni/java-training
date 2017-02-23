package jpl.ch14.ex06;

import java.util.ArrayList;
import java.util.List;

public class TimeNotifier {
	private int count;

	public synchronized int getCount() {
		return count;
	}

	public void countUp(int stop) throws InterruptedException {
		while (getCount() < stop) {
			Thread.sleep(1000);
			synchronized (this) {
				count++;
				notifyAll();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final TimeNotifier notifier = new TimeNotifier();
		final int stop = 21;

		final List<Thread> list = new ArrayList<>();
		list.add(new Thread(new MessagePrinter(notifier, 15, "msg15")));
		list.add(new Thread(new MessagePrinter(notifier, 7, "msg7")));

		list.forEach((Thread t) -> t.start());

		notifier.countUp(stop);

		list.forEach((Thread t) -> t.interrupt());
	}
}
