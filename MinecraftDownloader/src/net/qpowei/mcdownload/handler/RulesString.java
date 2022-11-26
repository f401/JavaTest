package net.qpowei.mcdownload.handler;

import net.qpowei.mcdownload.handler.value.VersionIndex;
import java.util.Set;
import java.util.Arrays;

public class RulesString
{
	private String stringField;
	private VersionIndex.Rules ruleField[];
	
	public boolean isString() {return stringField != null;};
	public boolean isRule() {return ruleField != null;}
	
	public Object get() {return stringField != null ? stringField : ruleField;}
	
	public String getString() {return stringField;}
	public VersionIndex.Rules[] getRules() {return ruleField;}
	
	public void setString(String needle) {
		stringField = needle;
		ruleField = null;
	}
	
	public void setRule(VersionIndex.Rules[] needle) {
		ruleField = needle;
		stringField = null;
	}

	@Override
	public String toString() {
		return String.format("RulesString {%s}", isString() ? getString() : Arrays.toString(getRules()));
	}
}
