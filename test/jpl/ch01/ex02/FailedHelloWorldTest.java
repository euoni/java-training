package jpl.ch01.ex02;

import org.junit.Test;

public class FailedHelloWorldTest {
	@Test(expected = ArithmeticException.class)
	public void test() {
		FailedHelloWorld.main(null);
	}
}
