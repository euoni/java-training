package jpl.ch17.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch17.ex03.Resource;

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
	}

	@Test(expected = IllegalStateException.class)
	public void testGetResourceAfterShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		manager.shutdown();

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

		// wait for entering queue
		while (!manager.refs.keySet().iterator().next().isEnqueued())
			System.gc();

		manager.shutdown();

		sc.stop();
		sc.assertEquals("resource released");
	}

	@Test
	public void testShutdownAfterShutdown() throws InterruptedException {
		final ResourceManager manager = new ResourceManager();
		manager.shutdown();
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

		sc.stop();
		sc.assertEquals("");
	}
}
