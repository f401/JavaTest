package net.qpowei.mcdownload.mirror;

public interface IMirror
{
	public String getVersionListURL();
	
	public String getAssetRootURL();
	public String getLibrariesRootURL();
	
	public String getMainJarRootURL();
	public String getJsonIndexesRootURL();
	
	public String getForgeRepositoryRootURL();
	
	public String getDisplayName();
}
