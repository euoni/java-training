package jpl.ch20.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

public class AttrTest {
	protected void testWrite(Attr attrSrc) throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		attrSrc.write(new DataOutputStream(outputStream));

		final byte[] buf = outputStream.toByteArray();

		final Attr attrDst = new Attr(new DataInputStream(new ByteArrayInputStream(buf)));
		assertThat(attrDst.getName(), is(attrSrc.getName()));
		assertThat(attrDst.getValue(), is(attrSrc.getValue()));
	}

	@Test
	public void testBoolean() throws IOException {
		testWrite(new Attr("name", true));
	}

	@Test
	public void testCharacter() throws IOException {
		testWrite(new Attr("name", 'v'));
	}

	@Test
	public void testByte() throws IOException {
		testWrite(new Attr("name", (byte) 1));
	}

	@Test
	public void testShort() throws IOException {
		testWrite(new Attr("name", (short) 1));
	}

	@Test
	public void testInteger() throws IOException {
		testWrite(new Attr("name", 1));
	}

	@Test
	public void testLong() throws IOException {
		testWrite(new Attr("name", 1L));
	}

	@Test
	public void testFloat() throws IOException {
		testWrite(new Attr("name", 1.f));
	}

	@Test
	public void testDouble() throws IOException {
		testWrite(new Attr("name", 1.));
	}

	@Test
	public void testString() throws IOException {
		testWrite(new Attr("name", "value"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testWriteUnsupported() throws IOException {
		final Attr attr = new Attr("name", new Object());

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		attr.write(new DataOutputStream(outputStream));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testReadUnsupported() throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final DataOutputStream stream = new DataOutputStream(outputStream);
		stream.writeUTF("name");
		stream.writeUTF("err");

		new Attr(new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray())));
	}

	@Test(expected = IOException.class)
	public void testReadIOException() throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final DataOutputStream stream = new DataOutputStream(outputStream);
		stream.writeByte(1);

		new Attr(new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray())));
	}
}
