package net.qpowei.filereader.android.dex.struct;

import java.util.Arrays;
/*
struct DexHeader {
	      u1  magic[8];           // includes version number 
	      u4  checksum;           // adler32 checksum
	      u1  signature[kSHA1DigestLen]; /* SHA-1 hash 
	      u4  fileSize;           // length of entire file 
	      u4  headerSize;         // offset to start of next section
	      u4  endianTag;
	      u4  linkSize;
	      u4  linkOff;
	      u4  mapOff;
	      u4  stringIdsSize;
	      u4  stringIdsOff;
	      u4  typeIdsSize;
	      u4  typeIdsOff;
	      u4  protoIdsSize;
	      u4  protoIdsOff;
	      u4  fieldIdsSize;
	      u4  fieldIdsOff;
	      u4  methodIdsSize;
	      u4  methodIdsOff;
	      u4  classDefsSize;
	      u4  classDefsOff;
	      u4  dataSize;
	      u4  dataOff;
};
*/
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
}
