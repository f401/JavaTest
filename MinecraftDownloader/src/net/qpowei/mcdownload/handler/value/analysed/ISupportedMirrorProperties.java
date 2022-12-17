package net.qpowei.mcdownload.handler.value.analysed;

import net.qpowei.mcdownload.mirror.IMirrorProvider;

public interface ISupportedMirrorProperties extends MinecraftJsonProperties
{
	public IMirrorProvider getMirrorProvider();
	public void setMirrorProvider(IMirrorProvider provider);
	public String getURL(IMirrorProvider provider);
}
