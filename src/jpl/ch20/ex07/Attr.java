package jpl.ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public void write(DataOutputStream stream) throws IOException {
		stream.writeUTF(name);
		if (value instanceof Boolean) {
			stream.writeUTF(Boolean.class.getName());
			stream.writeBoolean((Boolean) value);
		} else if (value instanceof Character) {
			stream.writeUTF(Character.class.getName());
			stream.writeChar((Character) value);
		} else if (value instanceof Byte) {
			stream.writeUTF(Byte.class.getName());
			stream.writeByte((Byte) value);
		} else if (value instanceof Short) {
			stream.writeUTF(Short.class.getName());
			stream.writeShort((Short) value);
		} else if (value instanceof Integer) {
			stream.writeUTF(Integer.class.getName());
			stream.writeInt((Integer) value);
		} else if (value instanceof Long) {
			stream.writeUTF(Long.class.getName());
			stream.writeLong((Long) value);
		} else if (value instanceof Float) {
			stream.writeUTF(Float.class.getName());
			stream.writeFloat((Float) value);
		} else if (value instanceof Double) {
			stream.writeUTF(Double.class.getName());
			stream.writeDouble((Double) value);
		} else if (value instanceof String) {
			stream.writeUTF(String.class.getName());
			stream.writeUTF((String) value);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public Attr(DataInputStream stream) throws IOException {
		name = stream.readUTF();
		final String type = stream.readUTF();
		if (type.equals(Boolean.class.getName())) {
			value = stream.readBoolean();
		} else if (type.equals(Character.class.getName())) {
			value = stream.readChar();
		} else if (type.equals(Byte.class.getName())) {
			value = stream.readByte();
		} else if (type.equals(Short.class.getName())) {
			value = stream.readShort();
		} else if (type.equals(Integer.class.getName())) {
			value = stream.readInt();
		} else if (type.equals(Long.class.getName())) {
			value = stream.readLong();
		} else if (type.equals(Float.class.getName())) {
			value = stream.readFloat();
		} else if (type.equals(Double.class.getName())) {
			value = stream.readDouble();
		} else if (type.equals(String.class.getName())) {
			value = stream.readUTF();
		} else {
			throw new UnsupportedOperationException();
		}
	}
}
