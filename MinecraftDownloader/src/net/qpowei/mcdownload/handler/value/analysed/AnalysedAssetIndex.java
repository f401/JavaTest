package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.IMirrorProvider;

public class AnalysedAssetIndex 
{
	private final AssetInfo objects[];

	public AnalysedAssetIndex(AssetInfo[] objects) {
		this.objects = objects;
	}
	
	public AssetInfo get(int index) {
		return objects[index];
	}
	
	public int size() {
		return objects.length;
	}
	
	public class AssetInfo extends AbstractSupportedMirrorProperties
	  implements ISupportedSizeProperties
	{

		private final String name, sha1;
		private final int size;

		public AssetInfo(String name, String sha1, int size) {
			this.name = name;
			this.sha1 = sha1;
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public String getSha1() {
			return sha1;
		}

		@Override
		public int getSize() {
			return size;
		}

		@Override
		public String getURL() {
			return provider.getAssetURLBySHA1(sha1);
		}

		@Override
		public String getURL(IMirrorProvider provider) {
			if (provider != null)
				return provider.getAssetURLBySHA1(sha1);
			else 
				return this.provider.getAssetURLBySHA1(sha1);//same as getURL()
		}

	}
}
