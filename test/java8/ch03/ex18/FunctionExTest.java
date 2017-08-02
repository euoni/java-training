package java8.ch03.ex18;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionExTest {
	@Test
	public void testCtor() {
		new FunctionEx();
	}

	@Test
	public void testUncheckedNotThrow() {
		assertThat(FunctionEx.unchecked(t -> t).apply(1), is(1));
	}

	@Test(expected = RuntimeException.class)
	public void testUncheckedThrowException() {
		FunctionEx.unchecked(t -> {
			throw new Exception();
		}).apply(1);
	}

	@Test(expected = InternalError.class)
	public void testUncheckedThrowThrowable() {
		FunctionEx.unchecked(t -> {
			throw new InternalError();
		}).apply(1);
	}
}
