package net.qpowei.mcdownload.handler.value;

import java.util.Map;

public class AssetsIndex
{
	public Map<String, AssetsInfo> objects;
	
	public static class AssetsInfo {
		public String hash;
		public int size;
	}
}
