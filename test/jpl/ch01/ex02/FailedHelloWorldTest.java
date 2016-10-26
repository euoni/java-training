package jpl.ch01.ex02;

import org.junit.Test;

public class FailedHelloWorldTest {
	@Test
	public void testFailedHelloWorld() {
		new FailedHelloWorld();
		return;
	}

	@Test(expected = ArithmeticException.class)
	public void testMain() {
		FailedHelloWorld.main(null);
	}
}
