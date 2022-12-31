package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.providers.IProviders;

public interface IMirrorProperties 
{
	public IProviders getMirrorProvider();
	public void setMirrorProvider(IProviders provider);
}
