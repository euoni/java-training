package interpret.component;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassList {
	private static final String CLASS_SUFFIX = ".class";
	private static final List<String> systemClasses = new ArrayList<>();
	private static final List<String> userClasses = new ArrayList<>();

	{
		// load system class names
		for (final String path : System.getProperty("sun.boot.class.path")
				.split(System.getProperty("path.separator"))) {
			URL url = null;
			try {
				url = new URL("jar:file:" + path + "!/");
			} catch (final MalformedURLException e) {
				continue;
			}

			try {
				final JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
				final Enumeration<JarEntry> e = jar.entries();
				while (e.hasMoreElements()) {
					final JarEntry entry = e.nextElement();
					final String name = entry.getName();
					if (name.endsWith(CLASS_SUFFIX))
						systemClasses.add(name.replace(CLASS_SUFFIX, "").replace('/', '.'));
				}
				jar.close();
			} catch (final IOException e) {
				continue;
			}
		}

		Collections.sort(systemClasses);
	}

	public static List<String> getSystemClassList() {
		return new ArrayList<>(systemClasses);
	}

	public List<String> getUserClassList() {
		return new ArrayList<>(userClasses);
	}

	public List<String> getAllClassList() {
		final ArrayList<String> list = new ArrayList<>(systemClasses);
		list.addAll(userClasses);

		return list;
	}
}
