package jpl.ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	public static void translateByte(InputStream input, OutputStream output, byte from, byte to) throws IOException {
		int b;
		while ((b = input.read()) != -1)
			output.write(b == from ? to : b);
	}

	public static void main(String[] args) throws IOException {
		final byte from = (byte) args[0].charAt(0);
		final byte to = (byte) args[1].charAt(0);
		translateByte(System.in, System.out, from, to);
	}
}
