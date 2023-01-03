package net.qpowei.filereader.android.dex.value;

public class DexTypeList
{
	public int size;
	public DexTypeItem[] list;

	public DexTypeList(int size, DexTypeItem[] list) {
		this.size = size;
		this.list = list;
	}
	
	public short[] toShortArray() {
		short[] result = new short[list.length];
		for (int i = 0; i < list.length; ++i) {
			result[i] = list[i].index;
		}
		return result;
	}
	
}
