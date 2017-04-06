package jpl.ch17.ex04;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;

import jpl.ch17.ex03.Resource;

public class ResourceManager extends jpl.ch17.ex03.ResourceManager {
	public ResourceManager() {
		queue = new ReferenceQueue<>();
		refs = new HashMap<>();
		reaper = new ReaperThread();
		reaper.start();
	}

	class ReaperThread extends Thread {
		@Override
		public void run() {
			while (!shutdown || !refs.isEmpty()) {
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
					;
				}
			}
		}
	}
}
