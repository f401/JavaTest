package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.MCDConstants;

public abstract class AbstractMinecraftMirrorProperties implements IMinecraftMirrorProperties
{

	IProviders provider;
	
	public AbstractMinecraftMirrorProperties(IProviders provider) {
		this.provider = provider;
	}
	
	@Override
	public IProviders getProvider() {
		return provider;
	}

	@Override
	public void setProvider(IProviders provider) {
		this.provider = provider;
	}
}
