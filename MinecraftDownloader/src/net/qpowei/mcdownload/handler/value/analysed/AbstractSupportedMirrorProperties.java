package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.IMirrorProvider;

public abstract class AbstractSupportedMirrorProperties implements ISupportedMirrorProperties
{

	IMirrorProvider provider;
	
	@Override
	public IMirrorProvider getMirrorProvider() {
		return provider;
	}

	@Override
	public void setMirrorProvider(IMirrorProvider provider) {
		this.provider = provider;
	}
}
