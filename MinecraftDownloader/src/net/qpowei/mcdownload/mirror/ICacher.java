package net.qpowei.mcdownload.mirror;

import java.util.List;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.VersionProfile;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionList;

public interface ICacher
{
	AnalysedAssetIndex getShouldDownloadAssetsList(VersionProfile profile, AnalysedAssetIndex index);
	AnalysedVersionIndex.DependentLibrary[] getShouldDownloadLibraries(VersionProfile profile, AnalysedVersionIndex.DependentLibrary[] src);
	
	boolean shouldDownloadAssetsIndex(VersionProfile profile, String sha1);
	boolean shouldDownloadAssetsIndex(VersionProfile profile, AnalysedVersionIndex index);
	
	boolean shouldDownloadVersionIndex(VersionProfile profile, String sha1);
	boolean shouldDownloadVersionIndex(VersionProfile profile, AnalysedVersionList.Version ver);
	
	boolean shouldDownloadMainJar(VersionProfile profile, AnalysedVersionIndex index);
	boolean shouldDownloadMainJar(VersionProfile profile, String sha1);
}
