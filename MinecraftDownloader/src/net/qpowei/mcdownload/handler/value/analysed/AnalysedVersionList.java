package net.qpowei.mcdownload.handler.value.analysed;
import net.qpowei.mcdownload.mirror.IMirrorProvider;
import net.qpowei.mcdownload.handler.VersionListAnalyser;

public class AnalysedVersionList
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
	
	public static class Version extends AbstractSupportedMirrorProperties {

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
			return provider.injectMainJarURL(url);
		}
		
		@Override
		public String getURL(IMirrorProvider provider) {
			return provider != null ? provider.injectMainJarURL(url) : 
			    this.provider.injectMainJarURL(url);//same as getURL
		}
		
	}
}
