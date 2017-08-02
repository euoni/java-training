package java8.ch03.ex17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class AsyncUtil {
	public static List<Thread> doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
		final UnaryOperator<Runnable> call = (r) -> () -> {
			try {
				r.run();
			} catch (final Throwable t) {
				handler.accept(t);
			}
		};

		final List<Thread> t = new ArrayList<>();
		t.add(new Thread(call.apply(first)));
		t.add(new Thread(call.apply(second)));
		t.forEach(Thread::start);
		return t;
	}
}
