package net.qpowei.jremap;

public class MappingField implements IMappingable {

	private String realName, proguardName, type;

	public MappingField(String realName, String proguardName, String type) {
		this.realName = realName;
		this.proguardName = proguardName;
		this.type = type;
	}

	public String getType() {
		return type;
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
