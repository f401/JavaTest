package net.qpowei.mcdownload.handler;

import net.qpowei.mcdownload.handler.value.VersionList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class VersionListAnalyser
{
	public static VersionList.Version[] getReleaseList(VersionList ver) {
		return getListByString(ver, VersionList.TYPE_RELEASE);
	}
	
	public static VersionList.Version[] getSnapshotList(VersionList ver) {
		return getListByString(ver, VersionList.TYPE_SNAPSHOT);
	}
	
	public static VersionList.Version[] getOldAlpha(VersionList ver) {
		return getListByString(ver, VersionList.TYPE_OLD_ALPHA);
	}
	
	public static VersionList.Version[] getOldBeta(VersionList ver) {
		return getListByString(ver, VersionList.TYPE_OLD_BETA);
	}
	
	public static VersionList.Version[] getListByString(VersionList ver, String needle) {
		List<VersionList.Version> result = new ArrayList<>();
		for (VersionList.Version version: ver.versions) {
			if (version.type.equals(needle)) {
				result.add(version);
			}
		}
		return result.toArray(new VersionList.Version[result.size()]);
	}
	
	public static VersionList.Version getVersionByString(VersionList ver, String name) {
		for (VersionList.Version version: ver.versions) {
		    if (version.type.equals(name)) {
				return version;
			}
		}
		return null;
	}
	
	public static String getSha1(String url) {
		String[] sp = url.split("/");
		return sp[sp.length - 2];
	}
}
