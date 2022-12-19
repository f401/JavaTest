package net.qpowei.mcdownload.mirror;

import net.qpowei.mcdownload.mirror.providers.IMirrorProvider;
import net.qpowei.mcdownload.util.FileUtils;
import java.io.File;

public class DefaultURLPath implements IURLPath
{
	public static final String ASSETS_DIR = "assets" + File.separator;
	
	private IMirrorProvider provider;

	public DefaultURLPath(IMirrorProvider provider) {
		this.provider = provider;
	}

	public void setProvider(IMirrorProvider provider) {
		this.provider = provider;
	}

	public IMirrorProvider getProvider() {
		return provider;
	}
	
	@Override
	public String getAssetsSavePath(String rootDir, String sha1) {
		return FileUtils.fixSeparator(rootDir) + ASSETS_DIR + sha1.substring(0, 2) + File.separator + sha1;
	}
	
	@Override
	public String getAssetsURLBySHA1(String sha1) {
		return provider.getMirror().getAssetRootURL() + sha1.substring(0, 2) + "/" + sha1;
	}
	
}
