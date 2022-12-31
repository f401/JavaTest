package net.qpowei.mcdownload;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;

public class VersionProfile
{
	private String name, versionName, root;
	private boolean enableSpilt;

	public VersionProfile(String name, String versionName, String root, boolean enableSpilt) {
		this.name = name;
		this.versionName = versionName;
		this.enableSpilt = enableSpilt;
		this.root = root;
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
}
