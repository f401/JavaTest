package net.qpowei.filereader.android.dex.value;

import java.util.ArrayList;
import java.util.Arrays;

import net.qpowei.filereader.android.dex.value.dexClass.DexClassData;
import net.qpowei.filereader.android.dex.value.dexClass.DexClassDef;
import net.qpowei.filereader.android.dex.value.dexIDs.DexFieldID;
import net.qpowei.filereader.android.dex.value.dexIDs.DexMethodID;

public class DexFile {
	public DexHeader header;
	public ArrayList<String> stringPool = new ArrayList<>();
	public ArrayList<Integer> types = new ArrayList<>();
	public ArrayList<DexProto> proto = new ArrayList<>();
	public ArrayList<DexFieldID> fields = new ArrayList<>();
	public ArrayList<DexMethodID> methods = new ArrayList<>();
	public ArrayList<DexClassDef> classDefs = new ArrayList<>();
	public ArrayList<DexFile.DexClass> classes = new ArrayList<>();

	@Override
	public String toString() {
		return "DexFile [header=" + header + ", \nstringPool=" + stringPool + ", \ntypes=" + types + ", \nproto=" + proto
				+ ", \nfields=" + fields + ", \nmethods=" + methods + ", classDefs=" + classDefs + ", \nclasses=" + classes
				+ "]";
	}

	public static class DexProto {
		public int shortyIdx;
		public int returnTypeIdx;
		public short[] paramIdx;
		@Override
		public String toString() {
			return "DexProto [shortyIdx=" + shortyIdx + ", returnTypeIdx=" + returnTypeIdx + ", paramIdx=" + Arrays.toString(paramIdx)
					+ "]";
		}
		public DexProto(int shortlyIdx, int returnTypeIdx, short[] paramIdx) {
			this.shortyIdx = shortlyIdx;
			this.returnTypeIdx = returnTypeIdx;
			this.paramIdx = paramIdx;
		}
	}

	public static class DexClass {
		public DexTypeList interfaces;
		public DexClassDef def;
		public DexClassData data;

		@Override
		public String toString() {
			return "DexClass [interfaces=" + interfaces + ", def=" + def + ", data=" + data + "]";
		}
	}
}
