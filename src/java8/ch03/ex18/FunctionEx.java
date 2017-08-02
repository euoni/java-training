package java8.ch03.ex18;

import java.util.function.Function;

public class FunctionEx {
	public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> f) {
		return (v) -> {
			try {
				return f.apply(v);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			} catch (final Throwable t) {
				throw t;
			}
		};
	}
}
