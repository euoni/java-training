package jpl.ch16.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PlayerLoader extends ClassLoader {
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			final byte[] buf = bytesForClass(name);
			return defineClass(null, buf, 0, buf.length);
		} catch (final IOException e) {
			throw new ClassNotFoundException(e.toString());
		}
	}

	protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
		InputStream in = null;
		try {
			in = streamFor(name + ".class");
			final int length = in.available();
			if (length == 0) {
				throw new ClassNotFoundException(name);
			}
			final byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		} finally {
			if (in != null)
				in.close();
		}
	}

	protected InputStream streamFor(String name) throws IOException {
		final URL url = getClass().getResource("/jpl/ch16/ex11/" + name);
		if (url == null)
			throw new IOException();
		return url.openStream();
	}
}
