package jpl.ch01.ex02;

import org.junit.Test;

public class HelloWorld2Test {
	@Test(expected = ArithmeticException.class)
	public void test() {
		HelloWorld2.main(null);
	}
}
