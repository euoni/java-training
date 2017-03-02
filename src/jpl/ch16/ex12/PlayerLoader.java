package jpl.ch16.ex12;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class PlayerLoader extends jpl.ch16.ex11.PlayerLoader {
	@Override
	protected InputStream streamFor(String name) throws IOException {
		final URL url = getClass().getResource("/jpl/ch16/ex12/" + name);
		if (url == null)
			throw new IOException();
		return url.openStream();
	}

	@Override
	protected URL findResource(String name) {
		return getClass().getResource("jpl/ch16/ex12/" + name);
	}

	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		return null;
	}
}
