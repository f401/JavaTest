package net.qpowei.mcdownload.handler.types;

import java.util.Arrays;

import net.qpowei.mcdownload.handler.value.VersionIndex;

public class StringAndRules {
	private String stringField;

	private VersionIndex.Rules ruleField[];
	private StringAndArray<String> valueField;

	public boolean isString() {
		return stringField != null;
	};

	public boolean isRule() {
		return ruleField != null;
	}

	public Object get() {
		return stringField != null ? stringField : ruleField;
	}

	public String getString() {
		return stringField;
	}

	public VersionIndex.Rules[] getRules() {
		return ruleField;
	}

	public StringAndArray<String> getValue() {
		return valueField;
	}

	public void setString(String needle) {
		stringField = needle;
		ruleField = null;
	}

	public void setRule(VersionIndex.Rules[] needle) {
		ruleField = needle;
		stringField = null;
	}

	public void setValue(StringAndArray<String> value) {
		this.valueField = value;
		stringField = null;
	}

	@Override
	public String toString() {
		return "StringAndRules [stringField=" + stringField + ", ruleField=" + Arrays.toString(ruleField)
				+ ", valueField=" + valueField + "]";
	}
}
