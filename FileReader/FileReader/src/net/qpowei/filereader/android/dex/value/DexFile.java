package net.qpowei.filereader.android.dex.value;

import java.util.ArrayList;
import java.util.Arrays;

public class DexFile {
	public DexHeader header;
	public ArrayList<String> stringPool = new ArrayList<>();
	public ArrayList<DexTypeId> types = new ArrayList<>();
	public ArrayList<DexProto> proto = new ArrayList<>();
	public ArrayList<DexFieldID> fields = new ArrayList<>();
	public ArrayList<DexMethodID> methods = new ArrayList<>();

	@Override
	public String toString() {
		return "DexFile [header=" + header + ",\n stringPool=" + stringPool + "\n, types=" + types + "\n, proto=" + proto + ", fields=" 
		        + fields + ",\n methods" + methods
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

	public static class DexTypeId {
		public int idx;//指向stringPool的索引

		public DexTypeId(int idx) {
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "DexTypeId [idx=" + idx + "]";
		}
	}
}
