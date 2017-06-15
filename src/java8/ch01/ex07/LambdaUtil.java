package java8.ch01.ex07;

public class LambdaUtil {
	public static Runnable andThen(Runnable first, Runnable second) {
		return () -> {
			first.run();
			second.run();
		};
	}

	public static void main(String[] args) {
		andThen(() -> System.out.println("one"), () -> System.out.println("two")).run();
	}
}
