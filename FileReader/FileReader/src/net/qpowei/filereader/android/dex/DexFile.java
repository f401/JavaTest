package net.qpowei.filereader.android.dex;

import java.util.ArrayList;
import java.util.Arrays;

public class DexFile {
	DexHeader header;
	public ArrayList<String> stringPool = new ArrayList<>();
	public ArrayList<DexTypeId> types = new ArrayList<>();
	public ArrayList<DexProto> proto = new ArrayList<>();

	@Override
	public String toString() {
		return "DexFile [header=" + header + ", stringPool=" + stringPool + ", types=" + types + ", proto=" + proto
				+ "]";
	}

	static class DexStringID {
		public int offset;

		public DexStringID(int offset) {
			this.offset = offset;
		}
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

	static class DexProtoID {
		public int shortlyIdx;//方法声明的字符串，指向 DexStringID的索引
		public int returnTypeIdx;//方法返回类型字符串，指向 DexTypeId 的索引
		public int paramsOffset;//下面的偏移
		
		@Override
		public String toString() {
			return "DexProtoID [shortlyIdx=" + shortlyIdx + ", returnTypeIdx=" + returnTypeIdx + ", paramsOffset="
					+ paramsOffset + "]";
		}

		public DexProtoID(int shortlyIdx, int returnTypeIdx, int paramsOffset) {
			this.shortlyIdx = shortlyIdx;
			this.returnTypeIdx = returnTypeIdx;
			this.paramsOffset = paramsOffset;
		}

		static class Params /* DexTypeList */ {
			public int size;
			public Param[] list;
			static class Param /* DexTypeItem */ {
				public short idx;//指向DexTypeId的索引
				public Param(short idx) {
					this.idx = idx;
				}
			}
			public Params(int size, Param[] list) {
				this.size = size;
				this.list = list;
			}

			short[] toShortArray() {
				short[] result = new short[size];
				for (int i = 0; i < size; ++i) {
					result[i] = list[i].idx;
				}
				return result;
			}
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
