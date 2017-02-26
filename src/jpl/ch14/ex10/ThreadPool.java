/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ch14.ex10;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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
	private volatile boolean isRunning;
	private final BlockingQueue<Runnable> queue;
	private final Thread[] worker;

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

		queue = new ArrayBlockingQueue<>(queueSize, true);
		worker = new Thread[numberOfThreads];
		for (int i = 0; i < worker.length; i++) {
			worker[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					while (isRunning || !queue.isEmpty()) {
						try {
							final Runnable head = queue.poll(1, TimeUnit.MILLISECONDS);
							if (head != null)
								head.run();
						} catch (final InterruptedException e) {
							return;
						}
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
		for (final Thread thread : worker) {
			try {
				thread.join();
			} catch (final InterruptedException e) {
				e.printStackTrace();
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

		try {
			queue.put(runnable);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
