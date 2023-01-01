package net.qpowei.mcdownload.handler.value;

import java.util.Map;

import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;

public class AssetsIndex
{
	public static class AssetsInfo {
		public String hash;
		public int size;

		@Override
		public String toString() {
			return "AssetsInfo [hash=" + hash + ", size=" + size + "]";
		}
	}

	public Map<String, AssetsInfo> objects;
	
	@Override
	public String toString() {
		return "AssetsIndex [objects=" + objects + "]";
	}
	
	public AnalysedAssetIndex analyse() {
		return AnalysedAssetIndex.analyse(this);
	}
}
