package net.qpowei.filereader.types.uleb128;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class Uleb128<T> {

	public static ArrayList<Byte> readAvaibleBytes(DataInput input) throws IOException {
		ArrayList<Byte> resultList = new ArrayList<>(5);
		byte highBit;
		do {
			byte bytes = input.readByte();
			highBit = (byte) (bytes & 0x80 /* 128 */);
			resultList.add((byte)(bytes & 0x7f));
		} while (highBit != 0);
		return resultList;
	}

	public static ArrayList<Byte> readAvaibleBytes(InputStream input) throws IOException {
		ArrayList<Byte> resultList = new ArrayList<>(5);
		byte highBit;
		do {
			byte bytes = (byte) (input.read() & 0xFF);
			highBit = (byte) (bytes & 0x80 /* 128 */);
			resultList.add((byte)(bytes & 0x7f));
		} while (highBit != 0);
		return resultList;
	}

	protected ArrayList<Byte> data;

	public Uleb128(ArrayList<Byte> data) {
		this.data = data;
	}

	public Uleb128(DataInput input) throws IOException {
		this(readAvaibleBytes(input));
	}

	public abstract T get();

}
