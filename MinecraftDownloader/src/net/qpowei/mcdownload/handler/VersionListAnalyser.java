package net.qpowei.mcdownload.handler;

import net.qpowei.mcdownload.handler.value.VersionList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import net.qpowei.mcdownload.handler.constants.VersionListTypes;

public class VersionListAnalyser
{
	
	public static String getSha1(String url) {
		String[] sp = url.split("/");
		return sp[sp.length - 2];
	}
}
