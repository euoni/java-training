package jpl.ch14.ex09;

import java.util.Collections;

public class ThreadTree {
	private final long interval;
	private Thread thread;

	public ThreadTree(long interval) {
		this.interval = interval;
	}

	public void startMonitor(ThreadGroup root) {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					display(root, 0);

					try {
						Thread.sleep(interval);
					} catch (final InterruptedException e) {
						return;
					}
				}
			}
		}, "Monitor");
		thread.start();
	}

	public void stopMonitor() throws InterruptedException {
		thread.interrupt();
		thread.join();
	}

	private void display(ThreadGroup root, int depth) {
		System.out.print(String.join("", Collections.nCopies(depth, "  ")));
		System.out.println("+ " + root.getName());
		depth++;

		final Thread[] threads = new Thread[root.activeCount()];
		root.enumerate(threads, false);
		for (final Thread thread : threads) {
			if (thread != null) {
				System.out.print(String.join("", Collections.nCopies(depth, "  ")));
				System.out.println("- " + thread.getName());
			}
		}

		final ThreadGroup[] groups = new ThreadGroup[root.activeGroupCount()];
		root.enumerate(groups, false);
		for (final ThreadGroup group : groups) {
			display(group, depth);
		}

		if (depth == 1)
			System.out.println();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadGroup root = new ThreadGroup("RootGroup");
		final ThreadGroup group1 = new ThreadGroup(root, "Group1(daemon)");
		group1.setDaemon(true);
		final ThreadGroup group2 = new ThreadGroup(root, "Group2");

		new Thread(group1, new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1100);
				} catch (final InterruptedException e) {
					return;
				}
			}
		}, "Thread(1s)").start();

		new Thread(group1, new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2100);
				} catch (final InterruptedException e) {
					return;
				}
			}
		}, "Thread(2s)").start();

		new Thread(group2, new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3100);
				} catch (final InterruptedException e) {
					return;
				}
			}
		}, "Thread(3s)").start();

		final ThreadTree threadTree = new ThreadTree(1000);
		threadTree.startMonitor(root);

		Thread.sleep(4100);

		threadTree.stopMonitor();
	}
}
