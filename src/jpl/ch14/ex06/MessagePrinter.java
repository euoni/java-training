package jpl.ch14.ex06;

public class MessagePrinter implements Runnable {
	private final TimeNotifier notifier;
	private final int interval;
	private final String message;

	public MessagePrinter(TimeNotifier notifier, int interval, String message) {
		this.notifier = notifier;
		this.interval = interval;
		this.message = message;
	}

	@Override
	public void run() {
		while (true) {
			int count = notifier.getCount();

			synchronized (notifier) {
				final int oldCount = count;
				while (oldCount == count) {
					try {
						notifier.wait();
					} catch (final InterruptedException e) {
						return;
					}
					count = notifier.getCount();
				}
			}

			if (count % interval == 0) {
				System.out.println(message);
			}
		}
	}
}
