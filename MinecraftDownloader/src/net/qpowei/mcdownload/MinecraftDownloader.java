package net.qpowei.mcdownload;

import net.qpowei.mcdownload.handler.JsonParser;
import java.io.File;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;
import net.qpowei.mcdownload.util.MultiFileDownloader;
import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionList;
import net.qpowei.mcdownload.util.RulesUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.io.IOException;
import java.util.jar.JarInputStream;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.io.InputStream;
import java.util.Enumeration;
import net.qpowei.mcdownload.util.SHA1Utils;
import java.io.FileOutputStream;

public class MinecraftDownloader {
	
	private MultiFileDownloader downloader;
	private IProviders providers;
	private VersionProfile profile;
	private MultiFileDownloader.DownloadEvent assetsEvent, jarEvent, jsonEvent;
	private List<AnalysedVersionIndex.DependentLibrary.Natives> extractList;

	public MinecraftDownloader(VersionProfile profile, MultiFileDownloader downloader, IProviders providers, 
	    MultiFileDownloader.DownloadEvent assetsEvent, MultiFileDownloader.DownloadEvent jarEvent, 
		MultiFileDownloader.DownloadEvent jsonEvent) {
		this.downloader = downloader;
		this.providers = providers;
		this.profile = profile;
		this.assetsEvent = assetsEvent;
		this.jarEvent = jarEvent;
		this.jsonEvent = jsonEvent;
		this.extractList = new ArrayList<>();
	}
	
	public MinecraftDownloader(VersionProfile profile, MultiFileDownloader downloader,
							   MultiFileDownloader.DownloadEvent assetsEvent, MultiFileDownloader.DownloadEvent jarEvent, 
							   MultiFileDownloader.DownloadEvent jsonEvent) {
		this(profile, downloader, MCDConstants.defaultProviders, assetsEvent, jarEvent, jsonEvent);
	}

	public void setProviders(IProviders providers) {
		this.providers = providers;
	}

	public IProviders getProviders() {
		return providers;
	}

	public void setDownloader(MultiFileDownloader downloader) {
		this.downloader = downloader;
	}

	public MultiFileDownloader getDownloader() {
		return downloader;
	}
	
	public void downloadAssets(File json) {
	     AnalysedAssetIndex index = JsonParser.parseAssetsIndex(json).analyse();
		 index = providers.getCacher().getShouldDownloadAssetsList(profile, index);
		 for (int i = 0; i < index.size(); ++i) {
			 	downloader.addIntoDownloadList(
				    index.get(i).getURL(), MCDConstants.defaultProviders.getURLPath().
					    getAssetsSavePath(profile, index.get(i).getSha1()));
		 }
		 downloader.startMultiThreadDownloadBlocking();
		 downloader.waitUntilDownloadFinish();
	}
	
	public void downloadLibraries(AnalysedVersionIndex.DependentLibrary[] libs) {
		for (AnalysedVersionIndex.DependentLibrary entry: libs) {
			
			if (entry.hasArtifact() && 
			   providers.getCacher().shouldDownloadLibrary(profile, entry)
			&& RulesUtils.shouldDownloadLibrary(entry.getRules())) {
			    downloader.addIntoDownloadList(entry.getURL(), providers.getURLPath().getLibrarySavePath(profile, entry));
			}
			
			if (entry.hasNative()) {
				if (providers.getCacher().shouldDownloadNativeLibrary(profile, entry.getNatives().getCurrentOSNative())) {
				    downloader.addIntoDownloadList(entry.getNatives().getCurrentOSNative().getURL(), providers.getURLPath().
				        getNativeLibrarySavePath(profile, entry.getNatives().getCurrentOSNative()));
				}
				extractList.add(entry.getNatives());
			}
		}
		downloader.startMultiThreadDownloadBlocking();
		downloader.waitUntilDownloadFinish();
	}
	
	public void downloadMainJar(AnalysedVersionIndex index) {
		if (providers.getCacher().shouldDownloadMainJar(profile, index)) {
			AnalysedVersionIndex.MinecraftMainJarJnfo client = 
			    index.getMainJar(AnalysedVersionIndex.MINECRAFT_CLIENT);
			downloader.downloadAsFileWithRetries(client.getURL(), 
			   new File(providers.getURLPath().getMainJarSavePathByProfile(profile)));
		}
	}
	
	public File downloadAssetsIndex(AnalysedVersionIndex index) {
		File saveTo = new File(providers.getURLPath().getAssetsIndexSavePathByProfile(profile));
		if (providers.getCacher().shouldDownloadAssetsIndex(profile, index)) 
			downloader.downloadAsFileWithRetries(index.getAssetsIndexURL(),
			    saveTo);
		return saveTo;
	}
	
	public File downloadVersionIndex(AnalysedVersionList.Version ver) {
		File saveTo = new File(providers.getURLPath()
		    .getVersionIndexSavePathByProfile(profile));
		if (providers.getCacher().shouldDownloadVersionIndex(profile, ver))
		    downloader.downloadAsFileWithRetries(ver.getURL(), saveTo);
		return saveTo;
	}
	
	public void downloadVersionList(File saveTo) {
		downloader.downloadAsFileWithRetries(providers.getMirror().getVersionListURL(), saveTo);
	}
	
	public MinecraftDownloader extractNatives() {
		for (AnalysedVersionIndex.DependentLibrary.Natives entry: extractList) {
			JarFile jfile = null;
			FileOutputStream fos = null;
			try {
				jfile = new JarFile(
				    providers.getURLPath().getNativeLibrarySavePath(profile, entry.getCurrentOSNative())
				);
			    Enumeration<JarEntry> e = jfile.entries();
			    while (e.hasMoreElements()) {
				     JarEntry jentry = e.nextElement();
				     String path = providers.getURLPath().getNativeExtractPath(profile) + jentry.getName();
				     if (!jentry.isDirectory() && entry.shouldExtract(jentry.getName())) {
					    new File(path).getParentFile().mkdirs();
					    InputStream is = jfile.getInputStream(jentry);
					    fos = new FileOutputStream(path);
					    byte[] buffer = new byte[1024];
					    int len = 0;
					    while((len = is.read(buffer)) > 0) {
						    fos.write(buffer, 0, len);
					    }
						
					    fos.close();
						fos = null;
				    }
			    }
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					jfile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this;
	}
	
	public MinecraftDownloader downloadGame() {
		File defaultList = new File(MCDConstants.defaultVersionListPath);
		if (defaultList.exists()) 
			downloadVersionList(defaultList);
		return downloadGame(defaultList);
	}
	
	public MinecraftDownloader downloadGame(File versionList) {
		AnalysedVersionList list = null;
		downloader.setEvent(jsonEvent);
		try {
		    list = JsonParser.parseVersionList(versionList).analyse();
		} catch (Exception e) {
			downloadVersionList(versionList);
			e.printStackTrace();
			list = JsonParser.parseVersionList(versionList).analyse();
		}
		File verIndex = downloadVersionIndex(list.getVersionByString(profile.getVersionName()));
		AnalysedVersionIndex aIndex = JsonParser.parseVersionIndex(verIndex).analyse();
		File assetsIndexFile = downloadAssetsIndex(aIndex);
		downloader.setEvent(jarEvent);
		downloadMainJar(aIndex);
		downloadLibraries(aIndex.getDependsLibrary());
		downloader.setEvent(assetsEvent);
		downloadAssets(assetsIndexFile);
		return this;
	}
	
}
