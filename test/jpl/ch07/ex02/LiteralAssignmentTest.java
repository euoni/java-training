package jpl.ch07.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class LiteralAssignmentTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		LiteralAssignment.main(null);

		sc.stop();
		sc.assertEquals(new String[] { "set char: val = 'A'", "char: 65(0x41)", "byte: 65(0x41)", "short: 65(0x41)",
				"int: 65(0x41)", "long: 65(0x41)", "float: 65.000000000", "double: 65.000000000", "",
				"set int: val = 4660(0x1234)", "char: 4660(0x1234)", "byte: 52(0x34)", "short: 4660(0x1234)",
				"int: 4660(0x1234)", "long: 4660(0x1234)", "float: 4660.000000000", "double: 4660.000000000", "",
				"set int: val = 305419896(0x12345678)", "char: 22136(0x5678)", "byte: 120(0x78)",
				"short: 22136(0x5678)", "int: 305419896(0x12345678)", "long: 305419896(0x12345678)",
				"float: 305419904.000000000", "double: 305419896.000000000", "",
				"set long: val = 1311768467294899695(0x1234567890abcdef)", "char: -12817(0xcdef)", "byte: -17(0xef)",
				"short: -12817(0xcdef)", "int: -1867788817(0x90abcdef)",
				"long: 1311768467294899695(0x1234567890abcdef)", "float: 1311768499227459580.000000000",
				"double: 1311768467294899710.000000000", "", "set float: val = 0.123457", "char: 0(0x0)",
				"byte: 0(0x0)", "short: 0(0x0)", "int: 0(0x0)", "long: 0(0x0)", "float: 0.123456791",
				"double: 0.123456791", "", "set double: val = 0.123457", "char: 0(0x0)", "byte: 0(0x0)",
				"short: 0(0x0)", "int: 0(0x0)", "long: 0(0x0)", "float: 0.123456791", "double: 0.123456789" });
	}
}
