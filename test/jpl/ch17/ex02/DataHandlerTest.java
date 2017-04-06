package jpl.ch17.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import org.junit.Test;

public class DataHandlerTest {
	@Test
	public void testReadFileGCData() throws IOException {
		final File tempFile = File.createTempFile("DataHandlerTest", null);
		tempFile.deleteOnExit();

		try (FileOutputStream writer = new FileOutputStream(tempFile)) {
			writer.write(ByteBuffer.allocate(4).putInt(3).array());
		}

		// first read
		final DataHandler handler = new DataHandler();
		byte[] data = handler.readFile(tempFile);
		assertThat(ByteBuffer.wrap(data).getInt(), is(3));

		// rewrite
		try (FileOutputStream writer = new FileOutputStream(tempFile)) {
			writer.write(ByteBuffer.allocate(4).putInt(50).array());
		}

		// test cache
		data = handler.readFile(tempFile);
		assertThat(ByteBuffer.wrap(data).getInt(), is(3));

		// gc data
		data = null;
		System.gc();

		// reload
		data = handler.readFile(tempFile);
		assertThat(ByteBuffer.wrap(data).getInt(), is(50));
	}

	@Test
	public void testReadFileGCFileData() throws IOException {
		File tempFile = File.createTempFile("DataHandlerTest", null);
		tempFile.deleteOnExit();

		try (FileOutputStream writer = new FileOutputStream(tempFile)) {
			writer.write(ByteBuffer.allocate(4).putInt(3).array());
		}

		// first read
		final DataHandler handler = new DataHandler();
		byte[] data = handler.readFile(tempFile);
		assertThat(ByteBuffer.wrap(data).getInt(), is(3));

		// rewrite
		try (FileOutputStream writer = new FileOutputStream(tempFile)) {
			writer.write(ByteBuffer.allocate(4).putInt(50).array());
		}

		// test cache
		data = handler.readFile(tempFile);
		assertThat(ByteBuffer.wrap(data).getInt(), is(3));

		// gc file and data
		final Path path = tempFile.toPath();
		data = null;
		tempFile = null;
		System.gc();

		// reload
		data = handler.readFile(path.toFile());
		assertThat(ByteBuffer.wrap(data).getInt(), is(50));
	}
}
