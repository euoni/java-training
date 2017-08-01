package java8.ch03.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class Mapper {
	public static <T, U> Future<U> map(Future<T> src, Function<T, U> f) {
		return new Future<U>() {
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return src.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return src.isCancelled();
			}

			@Override
			public boolean isDone() {
				return src.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return f.apply(src.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return f.apply(src.get(timeout, unit));
			}
		};
	}
}
