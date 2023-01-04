package net.qpowei.filereader.android.dex.value;

import net.qpowei.filereader.types.uleb128.IntUleb128;

public class DexField {
	public IntUleb128 fieldIdx;//指向DexFieldId的索引
	public IntUleb128 accessFlags;

	@Override
	public String toString() {
		return "DexField [fieldIdx=" + fieldIdx + ", accessFlags=" + accessFlags + "]";
	}

	public DexField(IntUleb128 fieldIdx, IntUleb128 accessFlags) {
		this.fieldIdx = fieldIdx;
		this.accessFlags = accessFlags;
	}
} 
