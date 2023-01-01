package net.qpowei.mcdownload.handler;

public class VersionListAnalyser
{
	public static String getSha1(String url) {
		String[] sp = url.split("/");
		return sp[sp.length - 2];
	}
}
