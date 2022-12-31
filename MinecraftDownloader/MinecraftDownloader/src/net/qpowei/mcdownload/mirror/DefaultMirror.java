package net.qpowei.mcdownload.mirror;

public class DefaultMirror implements IMirror
{

	private final String versionListURL, assetsRootURL, 
	    librariesRootURL, mainJarRootURL, jsonIndexesRootURL,
	forgeRepositoryRootURL, displayName;

	DefaultMirror(String versionListURL, String assetsRootURL, String librariesRootURL, String mainJarRootURL, String jsonIndexesRootURL, String forgeRepositoryRootURL, String displayName) {
		this.versionListURL = versionListURL;
		this.assetsRootURL = assetsRootURL;
		this.librariesRootURL = librariesRootURL;
		this.mainJarRootURL = mainJarRootURL;
		this.jsonIndexesRootURL = jsonIndexesRootURL;
		this.forgeRepositoryRootURL = forgeRepositoryRootURL;
		this.displayName = displayName;
	}

	
	
	@Override
	public String getDisplayName() {
		return displayName;
	}
	
	@Override
	public String getVersionListURL() {
		return versionListURL;
	}
	
	@Override
	public String getJsonIndexesRootURL() {
		return jsonIndexesRootURL;
	}
	
	@Override
	public String getMainJarRootURL() {
		return mainJarRootURL;
	}

	@Override
	public String getAssetRootURL() {
		return assetsRootURL;
	}

	@Override
	public String getLibrariesRootURL() {
		return librariesRootURL;
	}

	@Override
	public String getForgeRepositoryRootURL() {
		return forgeRepositoryRootURL;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof IMirror)) return false;
		IMirror dobj = (IMirror)obj;
		return dobj.getAssetRootURL().equals(assetsRootURL) && 
		dobj.getForgeRepositoryRootURL().equals(forgeRepositoryRootURL) &&
		dobj.getMainJarRootURL().equals(mainJarRootURL) &&
		dobj.getLibrariesRootURL().equals(librariesRootURL) &&
		dobj.getVersionListURL().equals(versionListURL);
	}

}
