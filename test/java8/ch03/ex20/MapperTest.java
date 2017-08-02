package java8.ch03.ex20;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MapperTest {
	@Test
	public void testCtor() {
		new Mapper();
	}

	@Test
	public void testMap() {
		final List<String> result = Mapper.map(Arrays.asList("", "a", "A", "@"), String::toLowerCase);
		assertThat(result, contains("", "a", "a", "@"));
	}
}
