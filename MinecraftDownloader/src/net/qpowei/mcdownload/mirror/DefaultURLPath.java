package net.qpowei.mcdownload.mirror;

import java.io.File;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.mirror.providers.IMirrorProvider;
import net.qpowei.mcdownload.util.FileUtils;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex.DependentLibrary;
import net.qpowei.mcdownload.VersionProfile;

public class DefaultURLPath implements IURLPath
{
	public static final String ASSETS_DIR = "assets" + File.separator;
	public static final String LIBRARY_DIR = "libraries" + File.separator;
	public static final String VERSION_DIR = "versions" + File.separator;

	private IMirrorProvider provider;

	public DefaultURLPath(IMirrorProvider provider) {
		this.provider = provider;
	}

	public void setProvider(IMirrorProvider provider) {
		this.provider = provider;
	}

	public IMirrorProvider getProvider() {
		return provider;
	}

	@Override
	public String getVersionDirByProfile(VersionProfile profile) {
		return FileUtils.fixSeparator(profile.getRootDir()) + VERSION_DIR + profile.getName() + File.separator;
	}

	@Override
	public String getAssetsIndexSavePathByProfile(VersionProfile profile) {
		return getAssetsDir(profile) + "index" + File.separator + profile.getVersionName() + ".json";
	}

	@Override
	public String getVersionIndexSavePathByProfile(VersionProfile profile) {
		return getVersionDirByProfile(profile) + profile.getVersionName() + ".json";
	}

	@Override
	public String getMainJarSavePathByProfile(VersionProfile profile) {
		return getVersionDirByProfile(profile) + profile.getVersionName() + ".jar";
	}
	
	@Override
	public String getLibraryPathByName(String src) {
		String[] list = src.split(":");
		return list[0].replaceAll("\\.", File.separator) + File.separator +
		    list[1] + File.separator + 
			list[2] + File.separator + 
			list[1] + "-" + list[2] + ".jar";
	}
	
	@Override
	public String getLibrarySavePath(VersionProfile profile, AnalysedVersionIndex.DependentLibrary src) {
		return getLibrarySavePathByName(profile, src.getName());
	}
	
	@Override
	public String getLibrarySavePathByName(VersionProfile profile, String name) {
		return getLibraryDir(profile) + getLibraryPathByName(name);
	}
	
	@Override
	public String getLibraryDir(VersionProfile profile) {
		return FileUtils.fixSeparator(profile.getRootDir()) + LIBRARY_DIR;
	}
	
	@Override
	public String getAssetsDir(VersionProfile profile) {
		return FileUtils.fixSeparator(profile.getRootDir()) + ASSETS_DIR;
	}
	
	@Override
	public String getAssetsSavePath(VersionProfile profile, String sha1) {
		return getAssetsDir(profile) + File.separator + "objects" + File.separator + sha1.substring(0, 2) + File.separator + sha1;
	}
	
	@Override
	public String getAssetsURLBySHA1(String sha1) {
		return provider.getMirror().getAssetRootURL() + sha1.substring(0, 2) + "/" + sha1;
	}
	
}
