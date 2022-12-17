package net.qpowei.mcdownload.mirror;

public interface IMirrorProvider
{
	public String getAssetRootUrl();
	public String getAssetURLBySHA1(String name);
	
	public String getMainJarRootURL();
	public String injectMainJarURL(String url);
}
