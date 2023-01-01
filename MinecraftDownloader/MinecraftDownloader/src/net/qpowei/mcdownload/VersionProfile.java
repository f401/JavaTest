package net.qpowei.mcdownload;

import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;

public class VersionProfile
{
	public static VersionProfile make(AnalysedVersionIndex src, String rootDir) {
		return make(src, src.getVersionName(), rootDir, false);
	}

	public static VersionProfile make(AnalysedVersionIndex src, String root, String name) {
		return make(src, name, root, false);
	}

	public static VersionProfile make(AnalysedVersionIndex src, String root, boolean spilt) {
		return make(src, src.getVersionName(), root, spilt);
	}

	public static VersionProfile make(AnalysedVersionIndex index, String name, String root, boolean enableSpilt) {
		return new VersionProfile(name, index.getVersionName(), root, enableSpilt);
	}

	private String name, versionName, root;

	private boolean enableSpilt;
	
	public VersionProfile(String name, String versionName, String root, boolean enableSpilt) {
		this.name = name;
		this.versionName = versionName;
		this.enableSpilt = enableSpilt;
		this.root = root;
	}

	@Override
	public String toString() {
		return "VersionProfile [name=" + name + ", versionName=" + versionName + ", root=" + root + ", enableSpilt="
				+ enableSpilt + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((versionName == null) ? 0 : versionName.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + (enableSpilt ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VersionProfile other = (VersionProfile) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (versionName == null) {
			if (other.versionName != null)
				return false;
		} else if (!versionName.equals(other.versionName))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (enableSpilt != other.enableSpilt)
			return false;
		return true;
	}

	public void setRootDir(String root) {
		this.root = root;
	}

	public String getRootDir() {
		return root;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public String getVersionName() {
		return versionName;
	}
	
	public void setEnableSpilt(boolean enableSpilt) {
		this.enableSpilt = enableSpilt;
	}
	
	public boolean isEnableSpilt() {
		return enableSpilt;
	}
}
