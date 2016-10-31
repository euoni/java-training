package jpl.ch02.ex06;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch02.ex04.VehicleTestHelper;

public class LinkedListTest extends VehicleTestHelper {
	@Test
	public void test() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		LinkedList.main(null);

		sc.stop();
		sc.assertEquals("head.value.id = 0", "head.value.speed = 60.0", "head.value.direction = 180.0",
				"head.value.owner = Java", "head.next.value.id = 1", "head.next.value.speed = 120.0",
				"head.next.value.direction = 0.0", "head.next.value.owner = JR");
	}
}
