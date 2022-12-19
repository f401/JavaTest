package net.qpowei.mcdownload.mirror;

import net.qpowei.mcdownload.mirror.providers.IMirrorProvider;

public class DefaultInjector implements IInjector
{
	private IMirrorProvider provider;
	private static final IMirror from = DefaultMirrors.MOJANG;

	public DefaultInjector(IMirrorProvider provider) {
		this.provider = provider;
	}

	public void setProvider(IMirrorProvider provider) {
		this.provider = provider;
	}

	public IMirrorProvider getProvider() {
		return provider;
	}

	@Override
	public String injectMainJarURL(String url) {
		if (url != null && !from.equals(provider.getMirror())) {
			return url.replaceFirst(from.getMainJarRootURL(), 
			   provider.getMirror().getMainJarRootURL());
		}
		return url;
	}

	@Override
	public String injectJsonIndexesURL(String url) {
		if (url != null && !from.equals(provider.getMirror())) {
			return url.replaceFirst(from.getJsonIndexesRootURL(), 
								   provider.getMirror().getJsonIndexesRootURL());
		}
		return url;
	}

	//same as main jar
	@Override
	public String injectLoggingFileURL(String url) {
		return injectMainJarURL(url);
	}

	@Override
	public String injectLibraryURL(String url) {
		if (url != null && !from.equals(provider.getMirror())) {
			return url.replaceFirst(from.getLibrariesRootURL(), 
			   provider.getMirror().getLibrariesRootURL());
		}
		return url;
	}
	
}
