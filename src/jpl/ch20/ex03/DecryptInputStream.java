package jpl.ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
	private static final int rot = 13;

	public DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		final int read = super.read();
		if (read == -1)
			return read;

		return (read < rot ? read + 256 : read) - rot;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		final int nread = super.read(b, off, len);
		final int last = off + nread;
		for (int i = off; i < last; i++)
			b[i] = (byte) ((b[i] < rot ? b[i] + 256 : b[i]) - rot);

		return nread;
	}
}
