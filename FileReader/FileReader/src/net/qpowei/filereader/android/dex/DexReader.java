package net.qpowei.filereader.android.dex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.qpowei.filereader.EndianRandomAccessFile;
import net.qpowei.filereader.android.dex.struct.DexHeader;

public class DexReader extends EndianRandomAccessFile {

	public DexReader(File file) throws FileNotFoundException {
		super(file, true);
	}

	public DexHeader readHeader() throws IOException {
		DexHeader header = new DexHeader();
		seek(0);
		readFully(header.magicNumber);

		// 这里可能就是为什么大部分解析dex都用C/C++写的原因了
		header.checksum = readInt();

		readFully(header.signature);

		header.fileSize = readInt();
		header.headerSize = readInt();

		header.endianTag = readInt();
		if (header.endianTag != 0x123456)
			throw new RuntimeException("不支持大端序");

		header.linkSize = readInt();
		header.linkOffset = readInt();
		header.mapOffset = readInt();
		header.stringIDSize = readInt();
		header.stringIDOffset = readInt();
		header.typeIDSize = readInt();
		header.typeIDOffset = readInt();
		header.protoIDSize = readInt();
		header.protoIDOffset = readInt();
		header.fieldIDSize = readInt();
		header.fieldIDOffset = readInt();
		header.methodIDSize = readInt();
		header.methodIDOffset = readInt();
		header.classDefinesSize = readInt();
		header.classDefinesOffset = readInt();
		header.dataSize = readInt();
		header.dataOffset = readInt();
		return header;
	}

}
