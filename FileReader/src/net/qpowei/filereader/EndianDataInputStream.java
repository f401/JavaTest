package net.qpowei.filereader;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EndianDataInputStream extends FilterInputStream implements DataInput {
	private DataInputStream stream;
	private boolean littleEndian;

	public EndianDataInputStream(InputStream stream, boolean littleEndian) {
		super(stream);
		this.stream = new DataInputStream(stream);
		this.littleEndian = littleEndian;
	}

	public void setLittleEndian(boolean littleEndian) {
		this.littleEndian = littleEndian;
	}

	public boolean isLittleEndian() {
		return littleEndian;
	}

	@Override
	public void readFully(byte[] p1) throws IOException {
		stream.readFully(p1);
	}

	@Override
	public void readFully(byte[] p1, int p2, int p3) throws IOException {
		stream.readFully(p1, p2, p3);
	}

	@Override
	public int skipBytes(int p1) throws IOException {
		return stream.skipBytes(p1);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return stream.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return stream.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return stream.readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		short result = stream.readShort();
		return littleEndian ? Short.reverseBytes(result) : result;
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return readShort();
	}

	@Override
	public char readChar() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int readInt() throws IOException {
		int result = stream.readInt();
		return littleEndian ? Integer.reverseBytes(result) : result;
	}

	@Override
	public long readLong() throws IOException {
		long result = stream.readLong();
		return littleEndian ? Long.reverseBytes(result) : result;
	}

	@Override
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(this.readInt());
	}

	@Override
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(this.readLong());
	}

	@Override
	public String readLine() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String readUTF() throws IOException {
		byte[] bytes = new byte[this.readShort()];
		this.readFully(bytes);
		return new String(bytes);
	}

	@Override
	public void close() throws IOException {
		super.close();
		stream.close();
	}
}
