package net.qpowei.mcdownload.util;

import net.qpowei.mcdownload.handler.value.VersionIndex;
import java.util.List;
import java.util.ArrayList;

public class RulesUtils
{
	public static final String ACTION_ALLOW = "allow";
	public static final String ACTION_DISALLOW = "disallow";
	
	public static boolean shouldDownloadLibrary(VersionIndex.Rules[] rule) {
	    List<String> allowedOS = new ArrayList<>();
	    for (VersionIndex.Rules entry: rule) {
			if (ACTION_ALLOW.equals(entry.action)) {
				if (entry.os != null) {
				    allowedOS.add(entry.os.name);
				} else {
					allowedOS.add(OperatingSystem.LINUX);
					allowedOS.add(OperatingSystem.WINDOWS);
					allowedOS.add(OperatingSystem.OSX);
				}
			} else {
				allowedOS.remove(entry.os.name);
			}
		}
		for (String os: allowedOS) {
			if (os.equalsIgnoreCase(OperatingSystem.CURRENT_OS)) {
				return true;
			}
		}
		return false;
	}
}
