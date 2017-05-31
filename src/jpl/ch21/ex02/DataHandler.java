package jpl.ch21.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.WeakHashMap;

public class DataHandler {
	private final WeakHashMap<File, byte[]> lastData = new WeakHashMap<>();

	byte[] readFile(File file) throws IOException {
		byte[] data;

		if (lastData.containsKey(file)) {
			data = lastData.get(file);
			if (data != null)
				return data;
		}

		data = readBytesFromFile(file);
		lastData.put(file, data);
		return data;
	}

	private static byte[] readBytesFromFile(File file) throws IOException {
		return Files.readAllBytes(file.toPath());
	}
}
