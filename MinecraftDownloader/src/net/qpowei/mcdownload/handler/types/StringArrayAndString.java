package net.qpowei.mcdownload.handler.types;

import java.util.Arrays;

public class StringArrayAndString 
{
	private String stringField;
	private String arrayField[];

	public boolean isString() {return stringField != null;};
	public boolean isArray() {return arrayField != null;}

	public Object get() {return stringField != null ? stringField : arrayField;}

	public String getString() {return stringField;}
	public String[] getArray() {return arrayField;}

	public void setString(String needle) {
		stringField = needle;
		arrayField = null;
	}

	public void setArray(String[] needle) {
		arrayField = needle;
		stringField = null;
	}

	@Override
	public String toString() {
		return String.format("StringArrayString {%s}", isString() ? getString() : Arrays.toString(getArray()));
	}
}
