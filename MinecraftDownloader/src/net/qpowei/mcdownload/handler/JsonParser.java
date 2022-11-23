package net.qpowei.mcdownload.handler;

import java.io.File;
import net.qpowei.mcdownload.Tools;
import net.qpowei.mcdownload.handler.value.VersionIndex;
import net.qpowei.mcdownload.handler.value.VersionList;
import net.qpowei.mcdownload.util.FileUtils;

public class JsonParser
{
	
	private JsonParser() {}
	
	public static VersionList parseVersionList(String path) {
		return parseVersionList(new File(path));
	}
	
	public static VersionList parseVersionList(File version) {
		 return Tools.GLOBAL_GSON.fromJson(FileUtils.readFileAsString(version), VersionList.class);
	}
	
	public static VersionIndex parseVersionIndex(String path) {
		return parseVersionIndex(new File(path));
	}
	
	public static VersionIndex parseVersionIndex(File file) {
		return Tools.GLOBAL_GSON.fromJson(FileUtils.readFileAsString(file), VersionIndex.class);
	}
	
}
