package net.qpowei.mcdownload.mirror;

import net.qpowei.mcdownload.mirror.providers.IProvidersSetters;

public class DefaultProviders implements IProvidersSetters
{
	
	private ICacher cacher;
	private IInjector injector;
	private IMirror mirror;
	private IURLPath urlPath;
	
	private DefaultProviders() {
		this(null, null, null);
	}
	
	public DefaultProviders(ICacher cacher, IInjector injector, IURLPath path) {
		this(cacher, injector, DefaultMirrors.MOJANG, path);
	}
	
	public DefaultProviders(ICacher cacher, IInjector injector, IMirror mirror, IURLPath urlPath) {
		this.cacher = cacher;
		this.injector = injector;
		this.mirror = mirror;
		this.urlPath = urlPath;
	}
	
	public DefaultInjector newDefaultInjector() {
		return new DefaultInjector(this);
	}
	
	public DefaultURLPath newDefaultURLPath() {
		return new DefaultURLPath(this);
	}
	
	public DefaultCacher newDefaultCacher() {
		return new DefaultCacher(this);
	}
	
	@Override
	public void setCacher(ICacher cacher) {
		this.cacher = cacher;
	}

	@Override
	public void setInjector(IInjector injector) {
		this.injector = injector;
	}

	@Override
	public void setMirror(IMirror mirror) {
		this.mirror = mirror;
	}
	
	@Override
	public void setURLPath(IURLPath urlPath) {
		this.urlPath = urlPath;
	}

	@Override
	public ICacher getCacher() {
		return cacher;
	}

	@Override
	public IInjector getInjector() {
		return injector;
	}

	@Override
	public IMirror getMirror() {
		return mirror;
	}

	@Override
	public IURLPath getURLPath() {
		return urlPath;
	}
	
	public static DefaultProviders create() {
		DefaultProviders result = new DefaultProviders();
		result.setInjector(result.newDefaultInjector());
		result.setURLPath(result.newDefaultURLPath());
		result.setCacher(result.newDefaultCacher());
		return result;
	}
	
}
