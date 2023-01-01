package net.qpowei.mcdownload.handler.types;

import java.util.Arrays;

public class StringAndArray<T> {

	private String stringField;
	private T[] arrayField;

	public boolean isString() {
		return stringField != null;
	};

	public boolean isArray() {
		return arrayField != null;
	}

	public Object get() {
		return stringField != null ? stringField : arrayField;
	}

	public void setString(String stringField) {
		this.stringField = stringField;
		this.arrayField = null;
	}

	public String getString() {
		return stringField;
	}

	public void setArray(T[] arrayField) {
		this.arrayField = arrayField;
		this.stringField = null;
	}

	public T[] getArray() {
		return arrayField;
	}

	@Override
	public String toString() {
		return "StringAndArray [stringField=" + stringField + ", arrayField=" + Arrays.toString(arrayField) + "]";
	}
}
