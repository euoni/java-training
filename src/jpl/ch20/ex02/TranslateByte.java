package jpl.ch20.ex02;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TranslateByte extends FilterInputStream {
	private final byte from;
	private final byte to;

	public TranslateByte(InputStream in, byte from, byte to) {
		super(in);

		this.from = from;
		this.to = to;
	}

	@Override
	public int read() throws IOException {
		final int read = super.read();
		if (read == from)
			return to;
		return read;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		final int nread = super.read(b, off, len);
		final int last = off + nread;
		for (int i = off; i < last; i++)
			if (b[i] == from)
				b[i] = to;

		return nread;
	}

	public static void main(String[] args) throws IOException {
		final byte from = (byte) args[0].charAt(0);
		final byte to = (byte) args[1].charAt(0);

		@SuppressWarnings("resource")
		final FilterInputStream input = new TranslateByte(System.in, from, to);
		final PrintStream output = System.out;

		int b;
		while ((b = input.read()) != -1)
			output.write(b);
	}
}
