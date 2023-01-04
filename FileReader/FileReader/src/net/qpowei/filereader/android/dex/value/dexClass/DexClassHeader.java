package net.qpowei.filereader.android.dex.value.dexClass;

import net.qpowei.filereader.types.uleb128.IntUleb128;

public class DexClassHeader {
	public IntUleb128 staticFieldsSize;
	public IntUleb128 instanceFieldsSize;
	public IntUleb128 directMethodsSize;
	public IntUleb128 virtualMethodsSize;

	public DexClassHeader(IntUleb128 staticFieldsSize, IntUleb128 instanceFieldsSize, IntUleb128 directMethodsSize,
			IntUleb128 virtualMethodsSize) {
		this.staticFieldsSize = staticFieldsSize;
		this.instanceFieldsSize = instanceFieldsSize;
		this.directMethodsSize = directMethodsSize;
		this.virtualMethodsSize = virtualMethodsSize;
	}

	@Override
	public String toString() {
		return "DexClassHeader [staticFieldsSize=" + staticFieldsSize + ", instanceFieldsSize=" + instanceFieldsSize
				+ ", directMethodsSize=" + directMethodsSize + ", virtualMethodsSize=" + virtualMethodsSize + "]";
	}
}
