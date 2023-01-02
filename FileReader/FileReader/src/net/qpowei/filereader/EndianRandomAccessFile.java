package net.qpowei.filereader;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EndianRandomAccessFile implements DataOutput, DataInput, Closeable {

	private RandomAccessFile real;

	private boolean littleEndian;

	public EndianRandomAccessFile(String str, boolean littleEndian) throws FileNotFoundException {
		this(new File(str), littleEndian);
	}
	public EndianRandomAccessFile(File file, boolean littleEndian) throws FileNotFoundException {
		this.real = new RandomAccessFile(file, "rw");
		this.littleEndian = littleEndian;
	}
	public boolean isLittleEndian() {
		return littleEndian;
	}
	public void setLittleEndian(boolean littleEndian) {
		this.littleEndian = littleEndian;
	}

	public long getFilePointer() throws IOException {
		return real.getFilePointer();
	}

	public void seek(long pos) throws IOException {
		real.seek(pos);
	}

	@Override
	public void close() throws IOException {
		real.close();
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		real.readFully(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		real.readFully(b, off, len);
	}

	@Override
	public int skipBytes(int n) throws IOException {
		return real.skipBytes(n);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return real.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return real.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return real.readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		return littleEndian ? Short.reverseBytes(real.readShort()) : real.readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return littleEndian ? Short.reverseBytes(real.readShort()) : real.readShort();
	}

	@Override
	public char readChar() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int readInt() throws IOException {
		return littleEndian ? Integer.reverseBytes(real.readInt()) : real.readInt();
	}

	@Override
	public long readLong() throws IOException {
		return littleEndian ? Long.reverseBytes(real.readLong()) : real.readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}

	@Override
	public double readDouble() throws IOException {
		return Double.doubleToLongBits(readDouble());
	}

	@Override
	public String readLine() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String readUTF() throws IOException {
		short len = readShort();
		byte[] bytes = new byte[len];
		readFully(bytes);
		return new String(bytes);
	}

	@Override
	public void write(int b) throws IOException {
		real.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		real.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		real.write(b, off, len);
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		real.writeBoolean(v);
	}

	@Override
	public void writeByte(int v) throws IOException {
		real.writeByte(v);
	}

	@Override
	public void writeShort(int v) throws IOException {
		real.writeShort(littleEndian ? Short.reverseBytes((short) v) : v);
	}

	@Override
	public void writeChar(int v) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writeInt(int v) throws IOException {
		real.writeInt(littleEndian ? Integer.reverseBytes(v) : v);
	}

	@Override
	public void writeLong(long v) throws IOException {
		real.writeLong(littleEndian ? Long.reverseBytes(v) : v);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		writeInt(Float.floatToIntBits(v));
	}

	@Override
	public void writeDouble(double v) throws IOException {
		writeLong(Double.doubleToLongBits(v));
	}

	@Override
	public void writeBytes(String s) throws IOException {
		real.writeBytes(s);
	}

	@Override
	public void writeChars(String s) throws IOException {
		real.writeChars(s);
	}

	@Override
	public void writeUTF(String s) throws IOException {
		writeShort(s.length());
		writeBytes(s);
	}

}
