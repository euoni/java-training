package jpl.ch17.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ResourceManagerTest {
	@Test
	public void testGetResource() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final Object key = new Object();
		manager.getResource(key);

		sc.stop();
		sc.assertEquals("resource initialized");

		// clean
		manager.shutdown();
		manager.reaper.join();
	}

	@Test(expected = IllegalStateException.class)
	public void testGetResourceAfterShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		manager.shutdown();
		manager.reaper.join();

		final Object key = new Object();
		manager.getResource(key);
	}

	@Test
	public void testUse() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		final Object key = new Object();
		final Resource resource = manager.getResource(key);

		// test use
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		resource.use(key);

		sc.stop();
		sc.assertEquals("resource used");

		// clean
		manager.shutdown();
		manager.reaper.join();
	}

	@Test
	public void testUseWithWrongKey() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		final Object key = new Object();
		final Resource resource = manager.getResource(key);

		// test use
		try {
			final Object anotherKey = new Object();
			resource.use(anotherKey);
		} catch (final IllegalArgumentException e) {
			// clean
			manager.shutdown();
			manager.reaper.join();
			return;
		}
		fail();
	}

	@Test
	public void testShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		Object key = new Object();
		manager.getResource(key);

		// test shutdown
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		key = null;
		System.gc();
		manager.shutdown();
		manager.reaper.join();

		sc.stop();
		sc.assertEquals("resource released");
	}

	@Test
	public void testShutdownAfterShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		manager.shutdown();
		manager.reaper.join();
		manager.shutdown();
	}

	@Test
	public void testShutdownAfterRelease() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		Object key = new Object();
		final Resource resource = manager.getResource(key);

		// force release
		StdoutCapture sc = new StdoutCapture();
		sc.start();

		resource.release();

		sc.stop();
		sc.assertEquals("resource released");

		// test shutdown
		sc = new StdoutCapture();
		sc.start();

		key = null;
		System.gc();
		manager.shutdown();
		manager.reaper.join();

		sc.stop();
		sc.assertEquals("");
	}
}
