package net.qpowei.filereader.android.dex.stream;

import java.io.IOException;
import java.io.InputStream;

import net.qpowei.filereader.EndianDataInputStream;
import net.qpowei.filereader.android.dex.DexException;
import net.qpowei.filereader.android.dex.value.DexFile;
import net.qpowei.filereader.android.dex.value.DexHeader;

public class DexInputStream extends EndianDataInputStream {

	public DexInputStream(InputStream is) {
		super(is, true);
	}

	private DexHeader readHeader() throws IOException {
		DexHeader header = new DexHeader();

		readFully(header.magicNumber);
		if (!header.compareMagic()) throw new DexException("Wrong Magic Number");

		header.checksum = readInt();

		readFully(header.signature);

		header.fileSize = readInt();
		header.headerSize = readInt();

		header.endianTag = readInt();
		setEndian(header);
		
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

	private void setEndian(DexHeader header) {
		if (header.endianTag == 0x12345678) {
			setLittleEndian(true);
		} else if (header.endianTag == 0x78563412) {
			setLittleEndian(false);
			System.err.println("WARNING: 对于大端序的文件目前还不是完全支持");
		} else {
			throw new DexException("Unknow Endian Tag " + Integer.toHexString(header.endianTag));
		}
	}

	public DexFile readFile() throws IOException {
		DexFile file = new DexFile();
		file.header = readHeader();
		return file;
	}
}
