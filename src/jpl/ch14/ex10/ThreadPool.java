/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ch14.ex10;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 * [Instruction]
 * Implement one constructor and three methods.
 * Don't forget to write a Test program to test this class.
 * Pay attention to @throws tags in the javadoc.
 * If needed, you can put "synchronized" keyword to methods.
 * All classes for implementation must be private inside this class.
 * Don't use java.util.concurrent package.
 */
public class ThreadPool {
	private final int queueSize;
	private final Queue<Runnable> queue;
	private final Thread[] worker;
	private volatile boolean isRunning;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads
	 *             is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1)
			throw new IllegalArgumentException();

		this.queueSize = queueSize;
		queue = new LinkedList<>();
		worker = new Thread[numberOfThreads];
		for (int i = 0; i < worker.length; i++) {
			worker[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							Runnable head;
							synchronized (queue) {
								while (queue.isEmpty()) {
									queue.wait();
									if (!isRunning && queue.isEmpty())
										return;
								}
								head = queue.poll();
								queue.notifyAll();
							}
							head.run();
						}
					} catch (final InterruptedException e) {
						return;
					}
				}
			}, "Worker" + i);
		}
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public void start() {
		if (isRunning)
			throw new IllegalStateException();

		isRunning = true;
		Arrays.stream(worker).forEach(t -> t.start());
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public void stop() {
		if (!isRunning)
			throw new IllegalStateException();

		isRunning = false;

		while (Arrays.stream(worker).anyMatch(t -> t.isAlive())) {
			synchronized (queue) {
				queue.notifyAll();
			}
			for (final Thread thread : worker) {
				try {
					thread.join(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();
		if (!isRunning)
			throw new IllegalStateException();

		synchronized (queue) {
			while (queue.size() >= queueSize) {
				try {
					queue.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.add(runnable);
			queue.notifyAll();
		}
	}
}
