package net.qpowei.filereader.android.dex.value.dexClass;

import java.util.ArrayList;

import net.qpowei.filereader.android.dex.value.DexField;
import net.qpowei.filereader.android.dex.value.DexMethod;

public class DexClassData {
	public DexClassHeader header;
	public ArrayList<DexField> staticFields = new ArrayList<>();
	public ArrayList<DexField> instanceFields = new ArrayList<>();
	public ArrayList<DexMethod> directMethods = new ArrayList<>();
	public ArrayList<DexMethod> virtualMethods = new ArrayList<>();

	@Override
	public String toString() {
		return "DexClassData [header=" + header + ", staticFields=" + staticFields + ", instanceFields="
				+ instanceFields + ", directMethods=" + directMethods + ", virtualMethods=" + virtualMethods + "]";
	}
}
