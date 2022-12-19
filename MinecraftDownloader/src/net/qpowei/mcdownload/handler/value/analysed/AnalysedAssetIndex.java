package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.handler.value.AssetsIndex;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import net.qpowei.mcdownload.MCDConstants;

public class AnalysedAssetIndex implements IMirrorProperties
{
	
	private final AssetInfo objects[];
	private IProviders provider;
	
	public AnalysedAssetIndex(AssetInfo[] objects) {
		this.objects = objects;
		this.provider = MCDConstants.defaultProviders;
	}
	
	public static AnalysedAssetIndex analyse(AssetsIndex index) {
		ArrayList<AnalysedAssetIndex.AssetInfo> result = new ArrayList<>(index.objects.size());
		for (Map.Entry<String, AssetsIndex.AssetsInfo> entry : index.objects.entrySet()) {
			result.add(new AnalysedAssetIndex.AssetInfo(
			    entry.getKey(), entry.getValue().hash, entry.getValue().size));
		}
		return new AnalysedAssetIndex(result.toArray(new AnalysedAssetIndex.AssetInfo[result.size()]));
	}
	
	@Override
	public IProviders getMirrorProvider() {
		return provider;
	}

	@Override
	public void setMirrorProvider(IProviders provider) {
		setMirrorProvider(provider, true);
	}
	
	public void setMirrorProvider(IProviders provider, boolean async) {
		this.provider = provider;
        for (AssetInfo info: objects) {
			info.setMirrorProvider(provider);
		}
	}
	
	public AssetInfo get(int index) {
		return objects[index];
	}
	
	public int size() {
		return objects.length;
	}
	
	public static class AssetInfo extends AbstractMinecraftMirrorProperties
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

		/* json文件里不直接包含 */
		@Override
		public String getURL() {
			return provider.getURLPath().getAssetsURLBySHA1(sha1);
		}

		public String getURL(IProviders provider) {
			if (provider != null)
				return provider.getURLPath().getAssetsURLBySHA1(sha1);
			else 
				return this.provider.getURLPath().getAssetsURLBySHA1(sha1);//same as getURL()
		}

	}
}
