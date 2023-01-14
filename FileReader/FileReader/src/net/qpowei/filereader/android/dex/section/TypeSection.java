package net.qpowei.filereader.android.dex.section;

import java.io.IOException;

import net.qpowei.filereader.EndianRandomAccessFile;
import net.qpowei.filereader.android.dex.struct.DexHeader;

public class TypeSection extends BaseSection {

	private DexHeader header;
	private int[] strids;

	public TypeSection(DexHeader header) {
		this.header = header;
	}

	public int getStringIndex(int idx) {
		return strids[idx];
	}

	@Override
	public void read(EndianRandomAccessFile input) throws IOException {
		input.seek(header.typeIDOffset);
		strids = new int[header.typeIDSize];
		for (int i = 0; i < header.typeIDSize; ++i) {
			strids[i] = input.readInt();
		}
	}

} 
