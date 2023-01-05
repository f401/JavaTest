package net.qpowei.filereader.android.dex.value;

import net.qpowei.filereader.types.uleb128.IntUleb128;

public class DexMethod {
	public IntUleb128 methodIdx;//DexMethodId的索引
	public IntUleb128 accessFlags;
	public IntUleb128 codeOff;

	public DexCode code;

	@Override
	public String toString() {
		return "DexMethod [methodIdx=" + methodIdx + ", accessFlags=" + accessFlags + ", codeOff=" + codeOff + ", code="
				+ code + "]";
	}
	public DexMethod(IntUleb128 methodIdx, IntUleb128 accessFlags, IntUleb128 codeOff) {
		this.methodIdx = methodIdx;
		this.accessFlags = accessFlags;
		this.codeOff = codeOff;
	}
}
