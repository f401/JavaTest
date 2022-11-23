package net.qpowei.mcdownload.handler.value;

import java.util.Map;
import java.util.Arrays;

public class VersionList
{
	public static final String TYPE_OLD_ALPHA = "old_alpha";
    public static final String TYPE_OLD_BETA = "old_beta";
    public static final String TYPE_RELEASE = "release";
    public static final String TYPE_SNAPSHOT = "snapshot";
	
	public Map<String, String> latest;//release, snapshot
	public VersionList.Version versions[];
	
	//sha1 不存在! 需要VersionListParser#getSha1获得
	public static class Version extends URLProperties
	{
		public String id;//name
		public String type;//snapshot release old-alpha old-beta
		
		public String time;
		public String releaseTime;
		
		public int complianceLevel;//only for version_manifest_v2

		@Override
		public String toString() {
			return String.format("Version { url: %s, id: %s, type: %s, time: %s, releaseTime: %s, compilationLevel: %s }"
			, url, id, type, time, releaseTime, complianceLevel);
		}
	}

	@Override
	public String toString() {
		return "VersionList { " + "latest: " + latest
		+ ", Version: " + Arrays.toString(versions) + "}";
	}
}
