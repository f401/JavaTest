package net.qpowei.filereader.android.dex.value;

import java.util.Arrays;

public class DexHeader {
	public byte[] magicNumber = new byte[8];
	public int checksum; 
	public byte[] signature = new byte[20];

	public int fileSize;
	public int headerSize;
	public int endianTag;

	public int linkSize;
	public int linkOffset;

	public int mapOffset;
	
	public int stringIDSize;
	public int stringIDOffset;

	public int typeIDSize;
	public int typeIDOffset;

	public int protoIDSize;
	public int protoIDOffset;

	public int fieldIDSize;
	public int fieldIDOffset;

	public int methodIDSize;
	public int methodIDOffset;

	public int classDefinesSize;

	@Override
	public String toString() {
		return "DexHeader [magicNumber=" + Arrays.toString(magicNumber) + ", checksum=" + checksum + ", signature="
				+ Arrays.toString(signature) + ", fileSize=" + fileSize + ", headerSize=" + headerSize + ", endianTag="
				+ endianTag + ", linkSize=" + linkSize + ", linkOffset=" + linkOffset + ", mapOffset=" + mapOffset
				+ ", stringIDSize=" + stringIDSize + ", stringIDOffset=" + stringIDOffset + ", typeIDSize=" + typeIDSize
				+ ", typeIDOffset=" + typeIDOffset + ", protoIDSize=" + protoIDSize + ", protoIDOffset=" + protoIDOffset
				+ ", fieldIDSize=" + fieldIDSize + ", fieldIDOffset=" + fieldIDOffset + ", methodIDSize=" + methodIDSize
				+ ", methodIDOffset=" + methodIDOffset + ", classDefinesSize=" + classDefinesSize
				+ ", classDefinesOffset=" + classDefinesOffset + ", dataSize=" + dataSize + ", dataOffset=" + dataOffset
				+ "]";
	}

	public int classDefinesOffset;

	public int dataSize;
	public int dataOffset;
	
	public boolean compareMagic() {
		for (int i = 0; i < 8; ++i) {
			if (DexConstants.MAGIC_NUMBER[i] != magicNumber[i]) return false;
		}
		return true;
	}
}
