package net.qpowei.jremap;

import java.util.ArrayList;

public class MappingClass implements IMappingable {

	private String proguardName, realName;

	public MappingClass() {
		this(null, null);
	}

	public MappingClass(String proguardName, String realName) {
		this.proguardName = proguardName;
		this.realName = realName;
	}

	public void setProguardName(String proguardName) {
		this.proguardName = proguardName;
	}

	public void setRealName(String realName) {
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
