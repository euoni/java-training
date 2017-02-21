package jpl.ch14.ex02;

public final class PrintServer implements Runnable {
	private static PrintQueue requests = new PrintQueue();
	private static Thread thread;

	public PrintServer() {
		thread = new Thread(this);
		thread.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (Thread.currentThread() != thread) {
			return;
		}

		try {
			for (;;) {
				realPrint(requests.remove());
			}
		} catch (final InterruptedException e) {
			return;
		}
	}

	private void realPrint(PrintJob job) {
		job.print();
	}
}
