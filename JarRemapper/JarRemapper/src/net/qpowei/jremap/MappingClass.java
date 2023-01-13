package net.qpowei.jremap;

public class MappingClass implements IMappingable {
	private String proguardName, realName;

	public MappingClass(String proguardName, String realName) {
		this.proguardName = proguardName;
		this.realName = realName;
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
