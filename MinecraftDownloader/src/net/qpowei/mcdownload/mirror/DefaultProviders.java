package net.qpowei.mcdownload.mirror;

import net.qpowei.mcdownload.mirror.providers.IProviders;

public class DefaultProviders implements IProviders
{
	
	private ICacher cacher;
	private IInjector injector;
	private IMirror mirror;
	private IURLPath urlPath;
	
	private DefaultProviders() {
		this(null, null);
	}
	
	public DefaultProviders(IInjector injector, IURLPath path) {
		this(new DefaultCacher(), injector, DefaultMirrors.MOJANG, path);
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
	
	public void setCacher(ICacher cacher) {
		this.cacher = cacher;
	}

	public void setInjector(IInjector injector) {
		this.injector = injector;
	}

	public void setMirror(IMirror mirror) {
		this.mirror = mirror;
	}
	
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
		return result;
	}
	
}
