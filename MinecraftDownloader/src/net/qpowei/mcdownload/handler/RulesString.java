package net.qpowei.mcdownload.handler;

import net.qpowei.mcdownload.handler.value.VersionIndex;
import java.util.Set;

public class RulesString
{
	private String stringField;
	private VersionIndex.Rules ruleField[];
	
	public boolean isString() {return stringField != null;};
	public boolean isRule() {return ruleField != null;}
	
	public Object get() {return stringField != null ? stringField : ruleField;}
	
	public String getString() {return stringField;}
	public VersionIndex.Rules[] getRules() {return ruleField;}
	
	public boolean setString(String needle) {
		if (ruleField != null) {
			stringField = needle;
			return true;
		}
		return false;
	}
	
	public boolean setRule(VersionIndex.Rules[] needle) {
		if (stringField != null) {
			ruleField = needle;
			return true;
		}
		return false;
	}
}
