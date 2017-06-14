package java8.ch01.ex06;

@FunctionalInterface
public interface RunnableEx {
	public abstract void run() throws Throwable;

	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (final Throwable e) {
				throw new RuntimeException(e);
			}
		};
	}
}
