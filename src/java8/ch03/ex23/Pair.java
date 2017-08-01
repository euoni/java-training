package java8.ch03.ex23;

import java.util.function.Function;

@SuppressWarnings("serial")
public class Pair<T> extends javafx.util.Pair<T, T> {
	public Pair(T key, T value) {
		super(key, value);
	}

	public <U> Pair<U> map(Function<T, U> f) {
		return new Pair<>(f.apply(getKey()), f.apply(getValue()));
	}
}
