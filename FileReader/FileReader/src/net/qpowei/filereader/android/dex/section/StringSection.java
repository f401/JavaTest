package net.qpowei.filereader.android.dex.section;

import java.io.IOException;

import net.qpowei.filereader.EndianRandomAccessFile;
import net.qpowei.filereader.android.dex.struct.DexHeader;

public class StringSection extends BaseSection {

	private DexHeader header;
	private int[] strOffs;

	public StringSection(DexHeader header) {
		this.header = header;
	}

	public int[] getStringsOff() {
		if (strOffs == null) {
			throw new RuntimeException("还未初始化");
		}
		return strOffs;
	}

	@Override
	public void read(EndianRandomAccessFile input) throws IOException {
		input.seek(header.stringIDOffset);
		strOffs = new int[header.stringIDSize];
		for (int i = 0; i < header.stringIDSize; ++i) {
			strOffs[i] = input.readInt();
		}

	}

}
