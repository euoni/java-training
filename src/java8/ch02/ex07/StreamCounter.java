package java8.ch02.ex07;

import java.util.stream.Stream;

public class StreamCounter {
	public static <T> boolean isFinite(Stream<T> stream) {
		// 無限ストリームかどうか確かめるには無限時間必要で実行不可能
		return stream.reduce((a, b) -> b).isPresent();
	}
}
