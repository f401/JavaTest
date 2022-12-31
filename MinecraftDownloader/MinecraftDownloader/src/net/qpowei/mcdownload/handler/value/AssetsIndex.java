package net.qpowei.mcdownload.handler.value;

import java.util.Map;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;

public class AssetsIndex
{
	public Map<String, AssetsInfo> objects;
	
	public static class AssetsInfo {
		public String hash;
		public int size;
	}
	
	public AnalysedAssetIndex analyse() {
		return AnalysedAssetIndex.analyse(this);
	}
}
