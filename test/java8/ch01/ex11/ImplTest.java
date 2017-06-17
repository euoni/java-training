package java8.ch01.ex11;

import org.junit.Test;

public class ImplTest {
	@Test
	public void test() {
		new AbstractAbstract().f();
		new DefaultDefault().f();
		new StaticStatic();
		new AbstractDefault().f();
		new AbstractStatic().f();
		new DefaultStatic().f();
		new SAbstract().f();
		new SDefault().f();
		new SStatic().f();
		IStatic.f();
		JStatic.f();
	}
}
