package java8.ch03.ex02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.junit.Before;
import org.junit.Test;

public class LockUtilTest {
	private List<String> history;
	private TestLock lock;

	@Before
	public void setUp() throws Exception {
		history = new ArrayList<>();
		lock = new TestLock(history);
	}

	@Test
	public void testCtor() {
		new LockUtil();
	}

	@Test
	public void testWithLock() throws Exception {
		final Integer ret = LockUtil.withLock(lock, () -> {
			history.add("task");
			return 1;
		});

		assertThat(ret, is(1));
		assertThat(history, contains("lock", "task", "unlock"));
	}

	@Test
	public void testWithLockException() {
		try {
			LockUtil.withLock(lock, () -> {
				history.add("task");
				throw new RuntimeException();
			});
		} catch (final Exception e) {
		}

		assertThat(history, contains("lock", "task", "unlock"));
	}

	private static class TestLock implements Lock {
		private final List<String> history;

		public TestLock(List<String> history) {
			this.history = history;
		}

		@Override
		public void lock() {
			history.add("lock");
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void unlock() {
			history.add("unlock");
		}

		@Override
		public Condition newCondition() {
			throw new UnsupportedOperationException();
		}
	}
}
