package net.qpowei.mcdownload.handler;

import java.io.File;
import net.qpowei.mcdownload.MCDConstants;
import net.qpowei.mcdownload.handler.value.VersionIndex;
import net.qpowei.mcdownload.handler.value.VersionList;
import net.qpowei.mcdownload.util.FileUtils;
import net.qpowei.mcdownload.handler.value.AssetsIndex;

public class JsonParser
{
	
	private JsonParser() {}
	
	public static VersionList parseVersionList(String path) {
		return parseVersionList(new File(path));
	}
	
	public static VersionList parseVersionList(File version) {
		 return MCDConstants.GLOBAL_GSON.fromJson(FileUtils.readFileAsString(version), VersionList.class);
	}
	
	public static VersionIndex parseVersionIndex(String path) {
		return parseVersionIndex(new File(path));
	}
	
	public static VersionIndex parseVersionIndex(File file) {
		return MCDConstants.GLOBAL_GSON.fromJson(FileUtils.readFileAsString(file), VersionIndex.class);
	}
	
	public static AssetsIndex parseAssetsIndex(String path) {
		return parseAssetsIndex(new File(path));
	}
	
	public static AssetsIndex parseAssetsIndex(File path) {
        return MCDConstants.GLOBAL_GSON.fromJson(FileUtils.readFileAsString(path), AssetsIndex.class);
	}
}
