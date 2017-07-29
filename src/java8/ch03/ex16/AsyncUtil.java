package java8.ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AsyncUtil {
	// secondが例外を出したときのハンドリング方法がなくなってしまうので、3つ目のパラメーターは依然として必要
	public static <T> Thread doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second,
			Consumer<Throwable> handler) {
		final Thread t = new Thread() {
			@Override
			public void run() {
				T result = null;
				Throwable t = null;
				try {
					result = first.get();
				} catch (final Throwable e) {
					t = e;
				}

				try {
					second.accept(result, t);
				} catch (final Throwable e) {
					handler.accept(e);
				}
			}
		};
		t.start();
		return t;
	}
}
