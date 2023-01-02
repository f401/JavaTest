package net.qpowei.filereader.android.dex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.qpowei.filereader.EndianRandomAccessFile;

public class DexReaderWriter extends EndianRandomAccessFile {

	public DexReaderWriter(String file) throws FileNotFoundException {
		super(file, true);
	}

	private DexHeader readHeader() throws IOException {
		DexHeader header = new DexHeader();

		readFully(header.magicNumber);
		if (!header.compareMagic()) throw new DexException("Wrong Magic Number");
		
		//这里可能就是为什么大部分解析dex都用C/C++写的原因了
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

	private void readStringPool(DexFile file) throws IOException {
		ArrayList<DexFile.DexStringID> stringOffsets = new ArrayList<>(file.header.stringIDSize);
		long curr = getFilePointer();//保存文件读取前的文件指针

		/** 读取偏移 */
		seek(file.header.stringIDOffset);
		for (int i = 0; i < file.header.stringIDSize; ++i) {
			stringOffsets.add(new DexFile.DexStringID(readInt()));
		}

		/** 真正读取字符串 */
		for (DexFile.DexStringID off : stringOffsets) {
			seek(off.offset);
			int len = readUleb128AsInt();
			byte[] buffer = new byte[len];
			readFully(buffer);
			//检查是否以0结尾
			if (readByte() != 0) throw new DexException("字符串不以0为结尾, off: " + getFilePointer());

			file.stringPool.add(new String(buffer));
		}
		
		seek(curr);
	}

	private void readTypes(DexFile file) throws IOException {
		long curr = getFilePointer();

		seek(file.header.typeIDOffset);
		for (int i = 0; i < file.header.typeIDSize; ++i) {
			file.types.add(new DexFile.DexTypeId(readInt()));
		}

		seek(curr);
	}

	private void readProtos(DexFile file) throws IOException {
		long curr = getFilePointer();
		
		seek(file.header.protoIDOffset);

		ArrayList<DexFile.DexProtoID> ids = new ArrayList<>();
		for (int i = 0; i < file.header.protoIDSize; ++i) {
			ids.add(new DexFile.DexProtoID(readInt(), readInt(), readInt()));
		}

		System.out.println(ids);
		
		ArrayList<DexFile.DexProtoID.Params> protoParam = new ArrayList<>();
		for (DexFile.DexProtoID id : ids) {
			if (id.paramsOffset != 0) {
				seek(id.paramsOffset);
				int len = readInt();
				ArrayList<DexFile.DexProtoID.Params.Param> params = new ArrayList<>(len);
				for (int i = 0; i < len; ++i) {
					params.add(new DexFile.DexProtoID.Params.Param(readShort()));
				}
				protoParam.add(new DexFile.DexProtoID.Params(len, params.toArray(new DexFile.DexProtoID.Params.Param[params.size()])));
			} else {
				protoParam.add(null);
			}
		}

		if (protoParam.size() != ids.size()) throw new DexException("This exception should never be happened, " + protoParam.size() + ", but " + ids.size());

		//添加进DexFile.proto
		for (int i = 0; i < protoParam.size(); ++i) {
			DexFile.DexProtoID id = ids.get(i);
			file.proto.add(new DexFile.DexProto(id.shortlyIdx, id.returnTypeIdx, protoParam.get(i) == null ? null :
						protoParam.get(i).toShortArray()));
		}

		seek(curr);
	}

	public DexFile readDexFile() throws IOException {
		DexFile file = new DexFile();
		file.header = readHeader();
		readStringPool(file);
		readTypes     (file);
		readProtos    (file);
		return file;
	}

	private int readUleb128AsInt() throws IOException {
		List<Byte> resultList = new ArrayList<>(4);
		byte highBit;
		do {
			byte bytes = readByte();
			highBit = (byte) (bytes & 0x80 /* 128 */);
			resultList.add(bytes);
		} while (highBit != 0);

		if (isLittleEndian()) Collections.reverse(resultList);

		int result = 0;
		for (byte bytes : resultList) {
			result = (result << 7) | (bytes & 0x7f);
		}
		return result;
	}
}
