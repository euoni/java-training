package java8.ch03.ex16;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AsyncUtilTest {
	@Test
	public void testCtor() {
		new AsyncUtil();
	}

	@Test
	public void testDoInOrderAsyncFirstThrow() throws InterruptedException {
		final boolean[] firstCalled = { false };
		final Throwable[] secondThrowable = { null };
		final boolean[] handlerCalled = { false };

		final RuntimeException exception = new RuntimeException();

		AsyncUtil.doInOrderAsync(() -> {
			firstCalled[0] = true;
			throw exception;
		}, (r, t) -> {
			assertThat(r, nullValue());
			secondThrowable[0] = t;
		}, (t) -> {
			handlerCalled[0] = true;
		}).join();

		assertThat(firstCalled[0], is(true));
		assertThat(secondThrowable[0], is(exception));
		assertThat(handlerCalled[0], is(false));
	}

	@Test
	public void testDoInOrderAsyncSecondThrow() throws InterruptedException {
		final boolean[] firstCalled = { false };
		final boolean[] secondCalled = { false };
		final Throwable[] handlerThrowable = { null };

		final RuntimeException exception = new RuntimeException();

		AsyncUtil.doInOrderAsync(() -> {
			firstCalled[0] = true;
			return 1;
		}, (r, t) -> {
			assertThat(r, is(1));
			secondCalled[0] = true;
			throw exception;
		}, (t) -> {
			handlerThrowable[0] = t;
		}).join();

		assertThat(firstCalled[0], is(true));
		assertThat(secondCalled[0], is(true));
		assertThat(handlerThrowable[0], is(exception));
	}

	@Test
	public void testDoInOrderAsyncFirstSecondThrow() throws InterruptedException {
		final boolean[] firstCalled = { false };
		final Throwable[] secondThrowable = { null };
		final Throwable[] handlerThrowable = { null };

		final RuntimeException exception1 = new RuntimeException();
		final RuntimeException exception2 = new RuntimeException();

		AsyncUtil.doInOrderAsync(() -> {
			firstCalled[0] = true;
			throw exception1;
		}, (r, t) -> {
			assertThat(r, nullValue());
			secondThrowable[0] = t;
			throw exception2;
		}, (t) -> {
			handlerThrowable[0] = t;
		}).join();

		assertThat(firstCalled[0], is(true));
		assertThat(secondThrowable[0], is(exception1));
		assertThat(handlerThrowable[0], is(exception2));
	}
}
