package jpl.ch17.ex05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import jpl.ch17.ex03.Resource;

public class ResourceManager {
	protected ReferenceQueue<Object> queue;
	protected Map<Reference<?>, Resource> refs;
	protected boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<>();
		refs = new HashMap<>();
	}

	/**
	 * 開放可能な全リソースを開放する。呼び出し時点で参照キューにエンキューされていないリソースの開放は保証されない。
	 */
	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;

			release();
		}
	}

	/**
	 * 新しいリソース取得前に、開放可能な全リソースを開放する。
	 */
	public synchronized Resource getResource(Object key) {
		if (shutdown)
			throw new IllegalStateException();

		release();

		final Resource res = new ResourceImpl(key);
		final Reference<?> ref = new PhantomReference<>(key, queue);
		refs.put(ref, res);
		return res;
	}

	private void release() {
		while (true) {
			final Reference<?> ref = queue.poll();
			if (ref == null)
				break;

			final Resource res = refs.get(ref);
			refs.remove(ref);
			res.release();
			ref.clear();
		}
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
}
