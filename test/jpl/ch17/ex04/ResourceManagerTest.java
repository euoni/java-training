package jpl.ch17.ex04;

import java.util.Arrays;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ResourceManagerTest {
	@Test
	public void testShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();

		// get reaper thread
		final ThreadGroup tg = Thread.currentThread().getThreadGroup();
		final Thread[] threads = new Thread[tg.activeCount()];
		final int count = tg.enumerate(threads, false);
		final Thread reaper = threads[count - 1];

		final Object[] keys = new Object[10];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new Object();
			manager.getResource(keys[i]);
		}

		manager.shutdown();

		// test after shutdown
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		for (int i = 0; i < keys.length; i++) {
			keys[i] = null;
		}
		System.gc();
		reaper.join();

		sc.stop();
		final String[] expected = new String[keys.length];
		Arrays.fill(expected, "resource released");
		sc.assertEquals(expected);
	}
}
