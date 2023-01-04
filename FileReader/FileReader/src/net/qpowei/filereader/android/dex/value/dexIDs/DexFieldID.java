package net.qpowei.filereader.android.dex.value.dexIDs;

public class DexFieldID
{
	public short classIdx;//所属类，指向DexTypeId
	public short typeIdx;//类型，指向DexTypeId
	public int nameIdx;//名称，指向DexStringId

	public DexFieldID(short classIdx, short typeIdx, int nameIdx) {
		this.classIdx = classIdx;
		this.typeIdx = typeIdx;
		this.nameIdx = nameIdx;
	}

	@Override
	public String toString() {
		return "DexFieldID [classIdx=" + classIdx + ", typeIdx=" + typeIdx + ", nameIdx= " + nameIdx + "]";
	}
}
