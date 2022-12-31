package net.qpowei.mcdownload.mirror;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.VersionProfile;

public interface IURLPath
{
	public String getAssetsURLBySHA1(String sha1);
	
	public String getAssetsSavePath(VersionProfile profile, String sha1);
	public String getAssetsDir(VersionProfile root);

	public String getLibraryDir(VersionProfile root);
	public String getLibrarySavePath(VersionProfile profile, AnalysedVersionIndex.DependentLibrary src);
	public String getNativeLibrarySavePath(VersionProfile profile, AnalysedVersionIndex.DependentLibrary.Natives.Native src);
	
	public String getMainJarSavePathByProfile(VersionProfile profile);
	public String getVersionDirByProfile(VersionProfile profile);
	
	public String getAssetsIndexSavePathByProfile(VersionProfile profile);
	public String getVersionIndexSavePathByProfile(VersionProfile profile);
	
	public String getNativeExtractPath(VersionProfile profile);
}
