package net.qpowei.mcdownload.handler.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.qpowei.mcdownload.handler.AbstractSupportedGetVersion;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionList;
import net.qpowei.mcdownload.mirror.providers.IProviders;

public class VersionList extends AbstractSupportedGetVersion<VersionList.Version> {

	// sha1 不存在! 需要VersionListParser#getSha1获得
	public static class Version {
		public String id;// name
		public String type;// snapshot release old-alpha old-beta

		public String time;
		public String releaseTime;

		public String url;

		public int complianceLevel;// only for version_manifest_v2

		@Override
		public String toString() {
			return String.format(
					"Version { url: %s, id: %s, type: %s, time: %s, releaseTime: %s, compilationLevel: %s }",
					url, id, type, time, releaseTime, complianceLevel);
		}
	}

	public Map<String, String> latest;// release, snapshot

	public VersionList.Version versions[];

	@Override
	public String toString() {
		return "VersionList [latest=" + latest + ", versions=" + Arrays.toString(versions) + "]";
	}

	@Override
	public VersionList.Version[] getListByString(String needle) {
		List<VersionList.Version> result = new ArrayList<>();
		for (VersionList.Version version : this.versions) {
			if (version.type.equals(needle)) {
				result.add(version);
			}
		}
		return result.toArray(new VersionList.Version[result.size()]);
	}

	@Override
	public VersionList.Version getVersionByString(String name) {
		for (VersionList.Version version : this.versions) {
			if (version.type.equals(name)) {
				return version;
			}
		}
		return null;
	}

	public AnalysedVersionList analyse(IProviders providers) {
		return AnalysedVersionList.analyse(this, providers);
	}
}
