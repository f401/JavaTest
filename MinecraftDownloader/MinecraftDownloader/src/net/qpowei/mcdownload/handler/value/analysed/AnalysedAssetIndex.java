package net.qpowei.mcdownload.handler.value.analysed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import net.qpowei.mcdownload.MCDConstants;
import net.qpowei.mcdownload.handler.value.AssetsIndex;
import net.qpowei.mcdownload.mirror.providers.IProviders;

public class AnalysedAssetIndex implements IMirrorProperties {

	public static class AssetInfo extends AbstractMinecraftMirrorProperties implements ISupportedSizeProperties {

		private final String name, sha1;
		private final int size;

		public AssetInfo(String name, String sha1, int size, IProviders provider) {
			super(provider);
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
				return this.provider.getURLPath().getAssetsURLBySHA1(sha1);// same as getURL()
		}

	}

	public static AnalysedAssetIndex analyse(AssetsIndex index, IProviders provider) {
		ArrayList<AnalysedAssetIndex.AssetInfo> result = new ArrayList<>(index.objects.size());
		for (Map.Entry<String, AssetsIndex.AssetsInfo> entry : index.objects.entrySet()) {
			result.add(new AnalysedAssetIndex.AssetInfo(entry.getKey(), entry.getValue().hash,
					entry.getValue().size, provider));
		}
		return new AnalysedAssetIndex(result.toArray(new AnalysedAssetIndex.AssetInfo[result.size()]), provider);
	}

	private final AssetInfo objects[];

	private IProviders provider;

	public AnalysedAssetIndex(AssetInfo[] objects, IProviders provider) {
		this.objects = objects;
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "AnalysedAssetIndex [objects=" + Arrays.toString(objects) + ", provider=" + provider + "]";
	}

	@Override
	public IProviders getProvider() {
		return provider;
	}

	@Override
	public void setProvider(IProviders provider) {
		setMirrorProvider(provider, true);
	}

	public void setMirrorProvider(IProviders provider, boolean async) {
		this.provider = provider;
		for (AssetInfo info : objects) {
			info.setProvider(provider);
		}
	}

	public AssetInfo get(int index) {
		return objects[index];
	}

	public int size() {
		return objects.length;
	}
}
