package jpl.ch17.ex03;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	protected ReferenceQueue<Object> queue;
	protected Map<Reference<?>, Resource> refs;
	protected Thread reaper;
	protected boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<>();
		refs = new HashMap<>();
		reaper = new ReaperThread();
		reaper.start();
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown)
			throw new IllegalStateException();
		final Resource res = new ResourceImpl(key);
		final Reference<?> ref = new PhantomReference<>(key, queue);
		refs.put(ref, res);
		return res;
	}

	private static class ResourceImpl implements Resource {
		private final WeakReference<Object> keyRef;
		boolean needsRelease = false;

		ResourceImpl(Object key) {
			keyRef = new WeakReference<>(key);

			System.out.println("resource initialized");

			needsRelease = true;
		}

		@Override
		public void use(Object key, Object... args) {
			if (keyRef.get() != key)
				throw new IllegalArgumentException("wrong key");

			System.out.println("resource used");
		}

		@Override
		public synchronized void release() {
			if (needsRelease) {
				needsRelease = false;

				System.out.println("resource released");
			}
		}
	}

	private class ReaperThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					final Reference<?> ref = queue.remove();
					Resource res = null;
					synchronized (ResourceManager.this) {
						res = refs.get(ref);
						refs.remove(ref);
					}
					res.release();
					ref.clear();
				} catch (final InterruptedException ex) {
					break;
				}
			}
		}
	}
}
