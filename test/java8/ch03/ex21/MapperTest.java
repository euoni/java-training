package java8.ch03.ex21;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class MapperTest {
	@Test
	public void testCtor() {
		new Mapper();
	}

	@Test
	public void testMapCancel() throws InterruptedException, ExecutionException {
		final FutureTask<Integer> task = new FutureTask<>(() -> {
		}, 1);
		final Future<Integer> mapped = Mapper.map(task, x -> x + 1);
		assertThat(mapped.isCancelled(), is(false));

		mapped.cancel(false);
		assertThat(mapped.isCancelled(), is(true));
	}

	@Test
	public void testMapDone() throws InterruptedException, ExecutionException {
		final FutureTask<Integer> task = new FutureTask<>(() -> {
		}, 1);
		final Future<Integer> mapped = Mapper.map(task, x -> x + 1);
		assertThat(mapped.isDone(), is(false));

		task.run();
		assertThat(mapped.isDone(), is(true));
	}

	@Test
	public void testMapGet1() throws InterruptedException, ExecutionException {
		final FutureTask<Integer> task = new FutureTask<>(() -> {
		}, 1);
		final Future<Integer> mapped = Mapper.map(task, x -> x + 1);

		task.run();
		assertThat(mapped.get(), is(2));
	}

	@Test
	public void testMapGetWithTimeout() throws InterruptedException, ExecutionException, TimeoutException {
		final FutureTask<Integer> task = new FutureTask<>(() -> {
		}, 1);
		final Future<Integer> mapped = Mapper.map(task, x -> x + 1);

		task.run();
		assertThat(mapped.get(1, TimeUnit.MILLISECONDS), is(2));
	}

	@Test(expected = TimeoutException.class)
	public void testMapGetTimeoutError() throws InterruptedException, ExecutionException, TimeoutException {
		final FutureTask<Integer> task = new FutureTask<>(() -> {
		}, 1);
		final Future<Integer> mapped = Mapper.map(task, x -> x + 1);

		assertThat(mapped.get(1, TimeUnit.MILLISECONDS), is(2));
	}
}
