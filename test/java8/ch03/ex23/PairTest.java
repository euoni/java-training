package java8.ch03.ex23;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {
	@Test
	public void testMap() {
		final Pair<String> pair = new Pair<>("a", "b");
		final Pair<String> mapped = pair.map(String::toUpperCase);

		assertThat(mapped.getKey(), is("A"));
		assertThat(mapped.getValue(), is("B"));
	}
}
