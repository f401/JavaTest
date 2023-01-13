package net.qpowei.jremap;

public class MappingMethod implements IMappingable {

	private String realName, proguardName;
	private long startLine, endLine;
	private MethodDesc desc;

	public MappingMethod(String realName, String proguardName, MethodDesc desc, long startLine, long endLine) {
		this.realName = realName;
		this.proguardName = proguardName;
		this.desc = desc;
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public MethodDesc getDesc() {
		return desc;
	}

	public long getStartLine() {
		return startLine;
	}

	public long getEndLine() {
		return endLine;
	}

	@Override
	public String getRealName() {
		return realName;
	}

	@Override
	public String getProguardName() {
		return proguardName;
	}
	
} 
