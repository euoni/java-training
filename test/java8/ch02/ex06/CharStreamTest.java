package java8.ch02.ex06;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class CharStreamTest {
	@Test
	public void testCtor() {
		new CharStream();
	}

	@Test
	public void testEmpty() {
		final List<Character> list = CharStream.characterStream("").collect(Collectors.toList());

		assertThat(list, empty());
	}

	@Test
	public void testOne() {
		final List<Character> list = CharStream.characterStream("a").collect(Collectors.toList());

		assertThat(list, contains('a'));
	}

	@Test
	public void testMany() {
		final List<Character> list = CharStream.characterStream("abcdef").collect(Collectors.toList());

		assertThat(list, contains('a', 'b', 'c', 'd', 'e', 'f'));
	}
}
