package net.qpowei.mcdownload.mirror;

import java.io.File;
import java.util.ArrayList;
import net.qpowei.mcdownload.VersionProfile;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionList;
import net.qpowei.mcdownload.mirror.providers.IURLPathProvider;
import net.qpowei.mcdownload.util.SHA1Utils;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex.DependentLibrary.Natives.Native;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex.DependentLibrary;

public class DefaultCacher implements ICacher
{

	@Override
	public boolean shouldDownloadLibrary(VersionProfile profile, AnalysedVersionIndex.DependentLibrary src) {
		File path = new File(urlProvider.getURLPath().getLibrarySavePath(profile, src));
		return !(path.exists() && SHA1Utils.compareFileAndString(path, src.getSha1()));
	}

	@Override
	public boolean shouldDownloadNativeLibrary(VersionProfile profile, AnalysedVersionIndex.DependentLibrary.Natives.Native nat) {
	    File path = new File(urlProvider.getURLPath().getNativeLibrarySavePath(profile, nat));
		return !(path.exists() && SHA1Utils.compareFileAndString(path, nat.getSha1()));
	}
	
	private IURLPathProvider urlProvider;

	public DefaultCacher(IURLPathProvider urlProvider) {
		this.urlProvider = urlProvider;
	}

	public void setUrlProvider(IURLPathProvider urlProvider) {
		this.urlProvider = urlProvider;
	}

	public IURLPathProvider getUrlProvider() {
		return urlProvider;
	}
	
	@Override
	public boolean shouldDownloadVersionIndex(VersionProfile profile, AnalysedVersionList.Version ver) {
		return shouldDownloadVersionIndex(profile, ver.getSha1());
	}
	
	@Override
	public boolean shouldDownloadMainJar(VersionProfile profile, AnalysedVersionIndex index) {
		return shouldDownloadMainJar(profile, index.getMainJar(AnalysedVersionIndex.MINECRAFT_CLIENT).getSha1());
	}

	@Override
	public boolean shouldDownloadMainJar(VersionProfile profile, String sha1) {
		File json = new File(urlProvider.getURLPath().getMainJarSavePathByProfile(profile));
		return !(json.exists() && SHA1Utils.compareFileAndString(json, sha1));
	}
	
	@Override
	public boolean shouldDownloadVersionIndex(VersionProfile profile, String sha1) {
		File json = new File(urlProvider.getURLPath().getVersionIndexSavePathByProfile(profile));
		return !(json.exists() && SHA1Utils.compareFileAndString(json, sha1));
	}
	
	@Override
	public boolean shouldDownloadAssetsIndex(VersionProfile profile, AnalysedVersionIndex index) {
		return shouldDownloadAssetsIndex(profile, index.getAssetsIndexSHA1());
	}

	@Override
	public boolean shouldDownloadAssetsIndex(VersionProfile profile, String sha1) {
		File json = new File(urlProvider.getURLPath().getAssetsIndexSavePathByProfile(profile));
		return !(json.exists() && SHA1Utils.compareFileAndString(json, sha1));
	}

	@Override
	public AnalysedVersionIndex.DependentLibrary[] getShouldDownloadLibraries(VersionProfile profile, AnalysedVersionIndex.DependentLibrary[] src) {
		if (new File(profile.getRootDir()).exists() && 
		    new File(urlProvider.getURLPath().getLibraryDir(profile)).exists()) {
			ArrayList<AnalysedVersionIndex.DependentLibrary> result = new ArrayList<>();
			for (AnalysedVersionIndex.DependentLibrary entry:src) {
				if (entry.hasArtifact()) {
			    	if (shouldDownloadLibrary(profile, entry)) {
				    	result.add(entry);
				    }
			    } 
			}
			return result.toArray(new AnalysedVersionIndex.DependentLibrary[result.size()]);
		}
		return src;
	}
	
	@Override
	public AnalysedAssetIndex getShouldDownloadAssetsList(VersionProfile profile, AnalysedAssetIndex index) {
		if (new File(profile.getRootDir()).exists() && 
		    new File(urlProvider.getURLPath().getAssetsDir(profile)).exists()) {
			ArrayList<AnalysedAssetIndex.AssetInfo> result = new ArrayList<>();
			for (int i = 0; i < index.size(); ++i) {
			    File file = new File(urlProvider.getURLPath().getAssetsSavePath(profile, index.get(i).getSha1()));
				if (!(file.exists() && SHA1Utils.compareFileAndString(file, index.get(i).getSha1()))) {
					result.add(index.get(i));
				}
			}
			return new AnalysedAssetIndex(result.toArray(new AnalysedAssetIndex.AssetInfo[result.size()]), 
				index.getProvider());
		}
		return index;
	}
	
}
