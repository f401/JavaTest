package net.qpowei.filereader.android.dex.value;

public class DexMethodID
{
	public short classIdx;
	public short protoIdx;
	public int nameIdx;

	public DexMethodID(short classIdx, short protoIdx, int nameIdx) {
		this.classIdx = classIdx;
		this.protoIdx = protoIdx;
		this.nameIdx = nameIdx;
	}
}
