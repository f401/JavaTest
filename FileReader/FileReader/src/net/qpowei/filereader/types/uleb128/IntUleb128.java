package net.qpowei.filereader.types.uleb128;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class IntUleb128 extends Uleb128<Integer> {

	public IntUleb128(ArrayList<Byte> data, boolean littleEndian) {
		super(data);
		this.real = process(data, littleEndian);
	}

	public static int readInt(DataInput di, boolean littleEndian) throws IOException {
		return read(di, littleEndian).real;
	}

	public static IntUleb128 read(DataInput di, boolean littleEndian) throws IOException {
		return new IntUleb128(readAvaibleBytes(di), littleEndian);
	}

	private int real;

	@Override
	public Integer get() {
		return real;
	}

	@Override
	public String toString() {
		return Integer.toString(real);
	}

	private static int process(ArrayList<Byte> data, boolean littleEndian) {
		if (littleEndian) Collections.reverse(data);
		int result = 0;
		for (byte b : data) {
			result = (result << 7) | (b & 0x7f);
		}
		return result;
	}
}
