package jpl.ch14.ex09;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ThreadTreeTest {
	@Test
	public void testMain() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ThreadTree.main(null);

		sc.stop();
		sc.assertEquals("+ RootGroup", "  + Group1(daemon)", "    - Thread(1s)", "    - Thread(2s)", "  + Group2",
				"    - Thread(3s)", "", "+ RootGroup", "  + Group1(daemon)", "    - Thread(1s)", "    - Thread(2s)",
				"  + Group2", "    - Thread(3s)", "", "+ RootGroup", "  + Group1(daemon)", "    - Thread(2s)",
				"  + Group2", "    - Thread(3s)", "", "+ RootGroup", "  + Group2", "    - Thread(3s)", "",
				"+ RootGroup", "  + Group2");
	}
}
