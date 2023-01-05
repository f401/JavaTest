package net.qpowei.mcdownload.handler.value;

import java.util.Map;

import net.qpowei.mcdownload.handler.value.analysed.AnalysedAssetIndex;
import net.qpowei.mcdownload.mirror.providers.IProviders;

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
	
	public AnalysedAssetIndex analyse(IProviders providers) {
		return AnalysedAssetIndex.analyse(this, providers);
	}
}
