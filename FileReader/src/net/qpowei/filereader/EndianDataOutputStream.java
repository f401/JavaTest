package net.qpowei.filereader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class EndianDataOutputStream extends FilterOutputStream implements java.io.DataOutput {
	private DataOutputStream stream;
	private boolean littleEndian;

	public EndianDataOutputStream(OutputStream stream, boolean littleEndian) {
		super(stream);
		this.stream = new DataOutputStream(stream);
		this.littleEndian = littleEndian;

	}

	public void setLittleEndian(boolean littleEndian) {
		this.littleEndian = littleEndian;
	}

	public boolean isLittleEndian() {
		return littleEndian;
	}

	@Override
	public void writeInt(int p1) throws IOException {
		stream.writeInt(littleEndian ? Integer.reverseBytes(p1) : p1);
	}

	@Override
	public void writeBoolean(boolean p1) throws IOException {
		stream.writeBoolean(p1);
	}

	@Override
	public void writeByte(int p1) throws IOException {
		stream.writeByte(p1);
	}

	@Override
	public void writeShort(int p1) throws IOException {
		stream.writeShort(littleEndian ? Short.reverseBytes((short) p1) : p1);
	}

	@Override
	public void writeChar(int p1) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writeLong(long p1) throws IOException {
		stream.writeLong(littleEndian ? Long.reverseBytes(p1) : p1);
	}

	@Override
	public void writeFloat(float p1) throws IOException {
		this.writeInt(Float.floatToIntBits(p1));
	}

	@Override
	public void writeDouble(double p1) throws IOException {
		this.writeLong(Double.doubleToLongBits(p1));
	}

	@Override
	public void writeBytes(String p1) throws IOException {
		stream.writeBytes(p1);
	}

	@Override
	public void writeChars(String p1) throws IOException {
		stream.writeChars(p1);
	}

	@Override
	public void writeUTF(String p1) throws IOException {
		this.writeShort(p1.length());
		this.writeBytes(p1);
	}

	@Override
	public void close() throws IOException {
		super.close();
		stream.close();
	}
}
