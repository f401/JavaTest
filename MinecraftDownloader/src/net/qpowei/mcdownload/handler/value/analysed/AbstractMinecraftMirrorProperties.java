package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.MCDConstants;

public abstract class AbstractMinecraftMirrorProperties implements IMinecraftMirrorProperties
{

	IProviders provider;
	
	public AbstractMinecraftMirrorProperties() {
		this(MCDConstants.defaultProviders);
	}
	
	public AbstractMinecraftMirrorProperties(IProviders provider) {
		this.provider = provider;
	}
	
	@Override
	public IProviders getMirrorProvider() {
		return provider;
	}

	@Override
	public void setMirrorProvider(IProviders provider) {
		this.provider = provider;
	}
}
