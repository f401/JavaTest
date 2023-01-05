package net.qpowei.filereader.android.dex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.qpowei.filereader.EndianRandomAccessFile;
import net.qpowei.filereader.android.dex.value.DexCode;
import net.qpowei.filereader.android.dex.value.DexField;
import net.qpowei.filereader.android.dex.value.DexFile;
import net.qpowei.filereader.android.dex.value.DexHeader;
import net.qpowei.filereader.android.dex.value.DexMethod;
import net.qpowei.filereader.android.dex.value.DexTypeItem;
import net.qpowei.filereader.android.dex.value.DexTypeList;
import net.qpowei.filereader.android.dex.value.dexClass.DexClassData;
import net.qpowei.filereader.android.dex.value.dexClass.DexClassDef;
import net.qpowei.filereader.android.dex.value.dexClass.DexClassHeader;
import net.qpowei.filereader.android.dex.value.dexIDs.DexFieldID;
import net.qpowei.filereader.android.dex.value.dexIDs.DexMethodID;
import net.qpowei.filereader.android.dex.value.dexIDs.DexProtoID;
import net.qpowei.filereader.android.dex.value.dexIDs.DexStringID;
import net.qpowei.filereader.types.uleb128.IntUleb128;

public class DexReader extends EndianRandomAccessFile {

	public DexReader(String file) throws FileNotFoundException {
		super(file, true);
	}

	private DexHeader readHeader() throws IOException {
		DexHeader header = new DexHeader();
		seek(0);
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
		ArrayList<DexStringID> stringOffsets = new ArrayList<>(file.header.stringIDSize);
		long curr = getFilePointer();//保存文件读取前的文件指针

		/** 读取偏移 */
		seek(file.header.stringIDOffset);
		for (int i = 0; i < file.header.stringIDSize; ++i) {
			stringOffsets.add(new DexStringID(readInt()));
		}

		/** 真正读取字符串 */
		for (DexStringID off : stringOffsets) {
			seek(off.offset);
			int len = readUleb128AsInt();
			byte[] buffer = new byte[len];
			readFully(buffer);
			//检查是否以0结尾
			if (readByte() != 0) throw new DexException("字符串不以0为结尾, off: " + getFilePointer() + ", len " + len);

			file.stringPool.add(new String(buffer));
		}
		
		seek(curr);
	}

	private void readTypes(DexFile file) throws IOException {
		long curr = getFilePointer();

		seek(file.header.typeIDOffset);
		for (int i = 0; i < file.header.typeIDSize; ++i) {
			file.types.add(readInt());
		}

		seek(curr);
	}

	private DexTypeList readTypeList() throws IOException {
		int len = readInt();
		ArrayList<DexTypeItem> item = new ArrayList<>(len);
		for (int i = 0; i < len; ++i) {
			item.add(new DexTypeItem(readShort()));
		}
		return new DexTypeList(len, item.toArray(new DexTypeItem[item.size()]));
	}

	private void readProtos(DexFile file) throws IOException {
		long curr = getFilePointer();
		
		seek(file.header.protoIDOffset);

		ArrayList<DexProtoID> ids = new ArrayList<>();
		for (int i = 0; i < file.header.protoIDSize; ++i) {
			ids.add(new DexProtoID(readInt(), readInt(), readInt()));
		}

		ArrayList<DexTypeList> protoParam = new ArrayList<>();
		for (DexProtoID id : ids) {
			if (id.paramsOffset != 0) {
				seek(id.paramsOffset);
				protoParam.add(readTypeList());
			} else {
				protoParam.add(null);
			}
		}

		if (protoParam.size() != ids.size()) throw new DexException("This exception should never be happened, " + protoParam.size() + ", but " + ids.size());

		//添加进DexFile.proto
		for (int i = 0; i < protoParam.size(); ++i) {
			DexProtoID id = ids.get(i);
			file.proto.add(new DexFile.DexProto(id.shortlyIdx, id.returnTypeIdx, protoParam.get(i) == null ? null :
						protoParam.get(i).toShortArray()));
		}

		seek(curr);
	}

	private void readClassDefs(DexFile file) throws IOException {
		long curr = getFilePointer();
		seek(file.header.classDefinesOffset);
		for (int i = 0; i < file.header.classDefinesSize; ++i) {
			file.classDefs.add(new DexClassDef(readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt()));
		}
		seek(curr);
	}

	private DexCode readDexCode(int offset) throws IOException {
		long curr = getFilePointer();
		seek(offset);
		DexCode dc = new DexCode(readShort(), readShort(), readShort(), readShort(), readInt(), readInt());
		for (int i = 0; i < dc.insnsSize; ++i) {
			dc.insns[i] = readShort();
		} 
		seek(curr);
		return dc;
	}

	private void readClassData(DexFile file) throws IOException {
		long curr = getFilePointer();
		for (DexClassDef define : file.classDefs) {
			//TODO finish
			DexFile.DexClass data = new DexFile.DexClass();
			data.def = define;
			//read interfaces
			if (define.interfacesOffset != 0) {
				seek(define.interfacesOffset);
				data.interfaces = readTypeList();
			}
			//read class data
			if (define.classDataOffset != 0) {
				seek(define.classDataOffset);
				DexClassData cdata = new DexClassData();
				//header
				cdata.header = new DexClassHeader(readIntUleb128(), readIntUleb128(), readIntUleb128(), readIntUleb128());

				for (int i = 0; i < cdata.header.staticFieldsSize.get(); ++i) {
					cdata.staticFields.add(new DexField(readIntUleb128(), readIntUleb128()));
				}

				for (int i = 0; i < cdata.header.instanceFieldsSize.get(); ++i) {
					cdata.instanceFields.add(new DexField(readIntUleb128(), readIntUleb128()));
				}

				for (int i = 0; i < cdata.header.directMethodsSize.get(); ++i) {
					DexMethod method = new DexMethod(readIntUleb128(), readIntUleb128(), readIntUleb128());
					method.code = readDexCode(method.codeOff.get());
					cdata.directMethods.add(method);
				}

				for (int i = 0; i < cdata.header.virtualMethodsSize.get(); ++i) {
					DexMethod method = new DexMethod(readIntUleb128(), readIntUleb128(), readIntUleb128());
					method.code = readDexCode(method.codeOff.get());
					cdata.virtualMethods.add(method);
				}

				data.data = cdata;
			}
			file.classes.add(data);
		}
		seek(curr);
	}

	private IntUleb128 readIntUleb128() throws IOException {
		return IntUleb128.read(this, isLittleEndian());
	}

	private int readUleb128AsInt() throws IOException {
		return IntUleb128.readInt(this, isLittleEndian());
	}
	
	private void readMethods(DexFile file) throws IOException {
		long curr = getFilePointer();
		seek(file.header.methodIDOffset);
		for (int i = 0; i < file.header.methodIDSize;++i) {
			file.methods.add(new DexMethodID(readShort(), readShort(), readInt()));
		}
		seek(curr);
	}
	
	private void readFields(DexFile file) throws IOException {
		long curr = getFilePointer();
		seek(file.header.fieldIDOffset);
		for (int i = 0; i < file.header.fieldIDSize; ++i) {
			file.fields.add(new DexFieldID(readShort(), readShort(), readInt()));
		}
		seek(curr);
	}

	public DexFile readDexFile() throws IOException {
		DexFile file = new DexFile();
		file.header = readHeader();
		readStringPool(file);
		readTypes     (file);
		readProtos    (file);
		readFields    (file);
		readMethods   (file);
		readClassDefs   (file);
		readClassData   (file);
		return file;
	}
}
