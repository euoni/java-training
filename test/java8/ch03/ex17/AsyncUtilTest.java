package java8.ch03.ex17;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AsyncUtilTest {
	@Test
	public void testCtor() {
		new AsyncUtil();
	}

	@Test
	public void testDoInParallelAsyncFirstThrow() throws InterruptedException {
		final RuntimeException ex1 = new RuntimeException();
		final boolean[] secondCalled = { false };
		final List<Throwable> throwables = new ArrayList<>();

		final List<Thread> threads = AsyncUtil.doInParallelAsync(() -> {
			throw ex1;
		}, () -> {
			secondCalled[0] = true;
		}, (t) -> {
			throwables.add(t);
		});

		for (final Thread thread : threads)
			thread.join();

		assertThat(throwables, contains(ex1));
		assertThat(secondCalled[0], is(true));
	}

	@Test
	public void testDoInParallelAsyncSecondThrow() throws InterruptedException {
		final boolean[] firstCalled = { false };
		final RuntimeException ex2 = new RuntimeException();
		final List<Throwable> throwables = new ArrayList<>();

		final List<Thread> threads = AsyncUtil.doInParallelAsync(() -> {
			firstCalled[0] = true;
		}, () -> {
			throw ex2;
		}, (t) -> {
			throwables.add(t);
		});

		for (final Thread thread : threads)
			thread.join();

		assertThat(throwables, contains(ex2));
		assertThat(firstCalled[0], is(true));
	}

	@Test
	public void testDoInParallelAsyncFirstSecondThrow() throws InterruptedException {
		final RuntimeException ex1 = new RuntimeException();
		final RuntimeException ex2 = new RuntimeException();
		final List<Throwable> throwables = new ArrayList<>();

		final List<Thread> threads = AsyncUtil.doInParallelAsync(() -> {
			throw ex1;
		}, () -> {
			throw ex2;
		}, (t) -> {
			throwables.add(t);
		});

		for (final Thread thread : threads)
			thread.join();

		assertThat(throwables, anyOf(contains(ex1, ex2), contains(ex2, ex1)));
	}
}
