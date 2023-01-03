package net.qpowei.filereader.android.dex.value;

public class DexProtoID
{
	public int shortlyIdx;//方法声明的字符串，指向 DexStringID的索引
	public int returnTypeIdx;//方法返回类型字符串，指向 DexTypeId 的索引
	public int paramsOffset;//指向DexTypeList的偏移
	
	@Override
	public String toString() {
		return "DexProtoID [shortlyIdx=" + shortlyIdx + ", returnTypeIdx=" + returnTypeIdx + ", paramsOffset="
			+ paramsOffset + "]";
	}
	
	public DexProtoID(int shortlyIdx, int returnTypeIdx, int paramsOffset) {
		this.shortlyIdx = shortlyIdx;
		this.returnTypeIdx = returnTypeIdx;
		this.paramsOffset = paramsOffset;
	}
}
