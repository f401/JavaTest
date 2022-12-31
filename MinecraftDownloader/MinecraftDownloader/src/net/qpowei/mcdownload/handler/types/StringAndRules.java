package net.qpowei.mcdownload.handler.types;

import net.qpowei.mcdownload.handler.value.VersionIndex;
import java.util.Set;
import java.util.Arrays;

public class StringAndRules
{
	private String stringField;
	
	private VersionIndex.Rules ruleField[];
	private StringAndArray<String> valueField;
	
	public boolean isString() {return stringField != null;};
	public boolean isRule() {return ruleField != null;}
	
	public Object get() {return stringField != null ? stringField : ruleField;}
	
	public String getString() {return stringField;}
	public VersionIndex.Rules[] getRules() {return ruleField;}
	public StringAndArray getValue() {return valueField;}
	
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
		return String.format("RulesString {%s, value: %s}", isString() ? getString() : Arrays.toString(getRules()), valueField);
	}
}
