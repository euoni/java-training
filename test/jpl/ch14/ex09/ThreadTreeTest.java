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
		sc.assertEquals("java.lang.ThreadGroup[name=RootGroup,maxpri=10]",
				"    java.lang.ThreadGroup[name=Group1(daemon),maxpri=10]",
				"        Thread[Thread(1s),5,Group1(daemon)]", "        Thread[Thread(2s),5,Group1(daemon)]",
				"    java.lang.ThreadGroup[name=Group2,maxpri=10]", "        Thread[Thread(3s),5,Group2]", "",
				"java.lang.ThreadGroup[name=RootGroup,maxpri=10]",
				"    java.lang.ThreadGroup[name=Group1(daemon),maxpri=10]",
				"        Thread[Thread(1s),5,Group1(daemon)]", "        Thread[Thread(2s),5,Group1(daemon)]",
				"    java.lang.ThreadGroup[name=Group2,maxpri=10]", "        Thread[Thread(3s),5,Group2]", "",
				"java.lang.ThreadGroup[name=RootGroup,maxpri=10]",
				"    java.lang.ThreadGroup[name=Group1(daemon),maxpri=10]",
				"        Thread[Thread(2s),5,Group1(daemon)]", "    java.lang.ThreadGroup[name=Group2,maxpri=10]",
				"        Thread[Thread(3s),5,Group2]", "", "java.lang.ThreadGroup[name=RootGroup,maxpri=10]",
				"    java.lang.ThreadGroup[name=Group2,maxpri=10]", "        Thread[Thread(3s),5,Group2]", "",
				"java.lang.ThreadGroup[name=RootGroup,maxpri=10]", "    java.lang.ThreadGroup[name=Group2,maxpri=10]");
	}
}
