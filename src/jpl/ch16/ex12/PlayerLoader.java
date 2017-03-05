package jpl.ch16.ex12;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class PlayerLoader extends jpl.ch16.ex11.PlayerLoader {
	@Override
	protected InputStream streamFor(String name) throws IOException {
		return getClass().getResourceAsStream(name);
	}

	@Override
	protected URL findResource(String name) {
		final URL url = getClass().getResource("/jpl/ch16/ex12/data/" + name);
		return url;
	}

	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		throw new UnsupportedOperationException();
	}
}
