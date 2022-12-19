package net.qpowei.mcdownload.handler.value.analysed;

import java.util.ArrayList;
import java.util.List;
import net.qpowei.mcdownload.handler.AbstractSupportedGetVersion;
import net.qpowei.mcdownload.handler.VersionListAnalyser;
import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.handler.value.VersionList;
import net.qpowei.mcdownload.handler.constants.VersionListTypes;

public class AnalysedVersionList extends AbstractSupportedGetVersion<AnalysedVersionList.Version>
{
	
	private final String latestRelease;
	private final String latestSnapShot;
	private final Version[] versions;

	public AnalysedVersionList(String latestRelease, String latestSnapShot, Version[] versions) {
		this.latestRelease = latestRelease;
		this.latestSnapShot = latestSnapShot;
		this.versions = versions;
	}
	
	public Version getVersion(int idx) {
		return versions[idx];
	}
	
	public int size() {
		return versions.length;
	}

	public Version[] getVersions() {
		return versions;
	}

	public String getLatestRelease() {
		return latestRelease;
	}

	public String getLatestSnapShot() {
		return latestSnapShot;
	}
	
	@Override
	public AnalysedVersionList.Version[] getListByString(String needle) {
		List<AnalysedVersionList.Version> result = new ArrayList<>();
		for (AnalysedVersionList.Version version: this.versions) {
			if (version.type.equals(needle)) {
				result.add(version);
			}
		}
		return result.toArray(new AnalysedVersionList.Version[result.size()]);
	}

	@Override
	public AnalysedVersionList.Version getVersionByString(String name) {
		for (AnalysedVersionList.Version version: this.versions) {
		    if (version.type.equals(name)) {
				return version;
			}
		}
		return null;
	}
	
	public static AnalysedVersionList analyse(VersionList src) {
		ArrayList<Version> versions = new ArrayList<>(src.versions.length);
		for (VersionList.Version entry : src.versions) {
			versions.add(new Version(entry.id, entry.url, entry.type, entry.time, entry.releaseTime, entry.complianceLevel));
		}
		return new AnalysedVersionList(src.latest.get(VersionListTypes.TYPE_RELEASE), 
		    src.latest.get(VersionListTypes.TYPE_SNAPSHOT), 
		    versions.toArray(new Version[versions.size()]));
	}
	
	public static class Version extends AbstractMinecraftMirrorProperties {

		private final String name, url;
		private final String type;
		private final String time, releaseTime;
		
		private final int complianceLevel;//only for version_manifest_v2

		public Version(String name, String url, String type, String time, String releaseTime) {
			this(name, url, type, time, releaseTime, 0);
		}

		public Version(String name, String url, String type, String time, String releaseTime, int complianceLevel) {
			this.name = name;
			this.url = url;
			this.type = type;
			this.time = time;
			this.releaseTime = releaseTime;
			this.complianceLevel = complianceLevel;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public String getTime() {
			return time;
		}

		public String getReleaseTime() {
			return releaseTime;
		}

		public int getComplianceLevel() {
			return complianceLevel;
		}

		@Override
		public String getSha1() {
			return VersionListAnalyser.getSha1(url);
		}

		@Override
		public String getURL() {
			return provider.getInjector().injectJsonIndexesURL(url);
		}
		
		public String getURL(IProviders provider) {
			return provider != null ? provider.getInjector().injectJsonIndexesURL(url) : 
			    this.provider.getInjector().injectJsonIndexesURL(url);//same as getURL
		}
		
	}
}
