package jpl.ch24.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class DisplayCurrencyTest {
	@Test
	public void testDisplayCurrency() {
		new DisplayCurrency();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		DisplayCurrency.main(null);

		sc.stop();
		sc.assertEquals("CAD in en_CA:	$", "CAD in zh_CN:	CAD", "CAD in fr_FR:	CAD", "CAD in de_DE:	CAD",
				"CAD in it_IT:	CAD", "CAD in ja_JP:	CAD", "CNY in en_CA:	CNY", "CNY in zh_CN:	￥",
				"CNY in fr_FR:	CNY", "CNY in de_DE:	CNY", "CNY in it_IT:	CNY", "CNY in ja_JP:	CNY",
				"FRF in en_CA:	FRF", "FRF in zh_CN:	FRF", "FRF in fr_FR:	F", "FRF in de_DE:	FRF",
				"FRF in it_IT:	FRF", "FRF in ja_JP:	FRF", "DEM in en_CA:	DEM", "DEM in zh_CN:	DEM",
				"DEM in fr_FR:	DEM", "DEM in de_DE:	DM", "DEM in it_IT:	DEM", "DEM in ja_JP:	DEM",
				"ITL in en_CA:	ITL", "ITL in zh_CN:	ITL", "ITL in fr_FR:	ITL", "ITL in de_DE:	ITL",
				"ITL in it_IT:	L.", "ITL in ja_JP:	ITL", "JPY in en_CA:	JPY", "JPY in zh_CN:	JPY",
				"JPY in fr_FR:	JPY", "JPY in de_DE:	JPY", "JPY in it_IT:	JPY", "JPY in ja_JP:	￥");
	}
}
