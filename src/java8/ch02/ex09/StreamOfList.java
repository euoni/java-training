package java8.ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamOfList {
	private static <T> ArrayList<T> accum(ArrayList<T> a, ArrayList<T> b) {
		final ArrayList<T> ret = new ArrayList<>();
		ret.addAll(a);
		ret.addAll(b);
		return ret;
	}

	public static <T> ArrayList<T> flatten0(Stream<ArrayList<T>> stream) {
		return stream.reduce(StreamOfList::accum).orElse(new ArrayList<>());
	}

	public static <T> ArrayList<T> flatten1(Stream<ArrayList<T>> stream) {
		return stream.reduce(new ArrayList<>(), StreamOfList::accum);
	}

	public static <T> ArrayList<T> flatten2(Stream<ArrayList<T>> stream) {
		return stream.reduce(new ArrayList<>(), StreamOfList::accum, StreamOfList::accum);
	}
}
