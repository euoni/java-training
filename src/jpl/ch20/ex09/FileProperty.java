package jpl.ch20.ex09;

import java.io.File;
import java.io.IOException;

public class FileProperty {
	private final File[] files;

	public FileProperty(File... files) {
		this.files = files;
	}

	public void printProperty() throws IOException {
		for (final File file : files) {
			System.out.println(file);
			System.out.println("\t" + "getName(): " + file.getName());
			System.out.println("\t" + "getPath(): " + file.getPath());
			System.out.println("\t" + "getAbsolutePath(): " + file.getAbsolutePath());
			System.out.println("\t" + "getCanonicalPath(): " + file.getCanonicalPath());
			System.out.println("\t" + "getParent(): " + file.getParent());
			System.out.println("\t" + "exists(): " + file.exists());
			System.out.println("\t" + "canRead(): " + file.canRead());
			System.out.println("\t" + "canWrite(): " + file.canWrite());
			System.out.println("\t" + "isFile(): " + file.isFile());
			System.out.println("\t" + "isDirectory(): " + file.isDirectory());
			System.out.println("\t" + "isAbsolute(): " + file.isAbsolute());
			System.out.println("\t" + "isHidden(): " + file.isHidden());
			System.out.println("\t" + "lastModified(): " + file.lastModified());
			System.out.println("\t" + "length(): " + file.length());
			System.out.println();
		}
	}
}
