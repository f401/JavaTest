package net.qpowei.filereader.android.dex;

import java.io.PrintStream;
import java.util.Arrays;

import net.qpowei.filereader.android.dex.value.DexFile;
import net.qpowei.filereader.android.dex.value.dexIDs.DexFieldID;
import net.qpowei.filereader.android.dex.value.dexIDs.DexMethodID;

public class DexPrinter {
	public static void print(DexFile file, PrintStream ps) {
		ps.println("-------------------DexFile------------------------");
		ps.println("Header: ");
		ps.println("\tchecksum: " + file.header.checksum);
		ps.println("\tsignature: " + Arrays.toString(file.header.signature));
		ps.println("\tfile size: " + file.header.fileSize);
		ps.println("\theader size: " + file.header.headerSize);
		ps.println("\tlink size" + file.header.linkSize);
		ps.println("\tlink off: " + file.header.linkOffset);
		ps.println("\tmap off: " + file.header.mapOffset);
		ps.println("\tstring id size: " + file.header.stringIDSize);
		ps.println("\tstring id off: " + file.header.stringIDOffset);
		ps.println("\ttype id size: " + file.header.typeIDSize);
		ps.println("\ttype id off: " + file.header.typeIDOffset);
		ps.println("\tproto id size: " + file.header.protoIDSize);
		ps.println("\tproto id off: " + file.header.protoIDOffset);
		ps.println("\tfield id size: " + file.header.fieldIDSize);
		ps.println("\tfield id off: " + file.header.fieldIDOffset);
		ps.println("\tmethod id size: " + file.header.methodIDSize);
		ps.println("\tmethod id off: " + file.header.methodIDOffset);
		ps.println("\tclass defs size: " + file.header.classDefinesSize);
		ps.println("\tclass defs off: " + file.header.classDefinesOffset);
		ps.println("\tdata size: " + file.header.dataSize);
		ps.println("\tdata off: " + file.header.dataOffset);
		ps.println("--------------------String Pool----------------------");
		for (int i = 0; i < file.stringPool.size(); ++i) {
			ps.println(String.format("[%d]=========>%s", i, file.stringPool.get(i)));
		}
		ps.println("-------------------Types--------------------------");
		for (int i = 0; i < file.types.size(); ++i) {
			ps.println(String.format("[%d]=========>%d: %s", i, file.types.get(i),
					getTypeNameByIdx(file, i)));
		}
		ps.println("-------------------Protos------------------------");
		ps.println("\t声明字符串  返回类型  参数");
		for (int i = 0; i < file.proto.size(); ++i) {
			DexFile.DexProto proto = file.proto.get(i);
			ps.print(i + "\t");
			ps.print(file.stringPool.get(proto.shortyIdx) + "  "
					+ getTypeNameByIdx(file, proto.returnTypeIdx) + "  ");
			if (proto.paramIdx != null)
				for (short k : proto.paramIdx) {
					ps.print(getTypeNameByIdx(file, k) + " ");
				}
			ps.println();
		}
		ps.println("----------------Fields------------------------");
		ps.println("\t所属类  类型  名称");
		for (int i = 0; i < file.fields.size(); ++i) {
			DexFieldID ids = file.fields.get(i);
			ps.print(i + "\t");
			ps.print(getTypeNameByIdx(file, ids.classIdx) + "  ");
			ps.print(getTypeNameByIdx(file, ids.typeIdx) + "  ");
			ps.println(file.stringPool.get(ids.nameIdx));
		}
		ps.println("--------------Method ID----------------");
		ps.println("\t所属类  方法原型  名称");
		for (int i = 0; i < file.methods.size(); ++i) {
			DexMethodID ids = file.methods.get(i);
			ps.print(i + "\t");
			ps.print(getTypeNameByIdx(file, ids.classIdx) + "  ");
			ps.print(ids.protoIdx + "  ");
			ps.println(file.stringPool.get(ids.nameIdx));
		}
		ps.println("-------------Class Defs----------------");
	}

	private static String getTypeNameByIdx(DexFile file, int idx) {
		return file.stringPool.get(file.types.get(idx));
	}
}
