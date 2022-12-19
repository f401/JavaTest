package net.qpowei.mcdownload.mirror;

public interface IURLPath
{
	public String getAssetsURLBySHA1(String sha1);
	public String getAssetsSavePath(String rootDir, String sha1);
}
