package net.qpowei.filereader.android.dex.value.dexClass;

public class DexClassDef
{
	public int classIdx;//指向DexTypeID的索引
	public int accessFlags;
	public int superClassIdx;//指向DexTypeID的索引
	public int interfacesOffset;//指向DexTypeList的偏移
	public int sourceFileIdx;//指向DexStringID的索引
	public int annotationsOff;
	public int classDataOffset;//DexClassData的偏移
	public int staticValuesOffset;//DexEncodedArray偏移

	public DexClassDef(int classIdx, int accessFlags, int superClassIdx, int interfacesOffset, int sourceFileIdx, int annotationsOff, int classDataOffset, int staticValuesOffset) {
		this.classIdx = classIdx;
		this.accessFlags = accessFlags;
		this.superClassIdx = superClassIdx;
		this.interfacesOffset = interfacesOffset;
		this.sourceFileIdx = sourceFileIdx;
		this.annotationsOff = annotationsOff;
		this.classDataOffset = classDataOffset;
		this.staticValuesOffset = staticValuesOffset;
	}

	@Override
	public String toString() {
		return "DexClassDef [classIdx=" + classIdx + ", accessFlags=" + accessFlags + ", superClassIdx= " + superClassIdx + ", interfacesOffset= " + interfacesOffset + ", sourceFileIdx= " + sourceFileIdx + ", annotationsOff= " + annotationsOff + ", classDataOffset=" + classDataOffset + ", staticValuesOffset=" + staticValuesOffset + "]";
	}
}
