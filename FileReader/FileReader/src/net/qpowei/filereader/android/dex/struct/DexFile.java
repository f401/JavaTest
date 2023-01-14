package net.qpowei.filereader.android.dex.struct;

import net.qpowei.filereader.android.dex.section.StringSection;
import net.qpowei.filereader.android.dex.section.TypeSection;

public class DexFile {
	private DexHeader header;
	private StringSection stringIds;
	private TypeSection typeIds;

	public DexFile(DexHeader header) {
		this.stringIds = new StringSection(header);
		this.typeIds = new TypeSection(header);
		this.header = header;
	}

	public DexHeader getHeader() {
		return header;
	}

	public StringSection getStringSection() {
		return stringIds;
	}

	public StringSection getStringIds() {
		return stringIds;
	}

	public TypeSection getTypeIds() {
		return typeIds;
	}

} 
