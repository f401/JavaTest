package net.qpowei.mcdownload.mirror.providers;

import net.qpowei.mcdownload.mirror.ICacher;
import net.qpowei.mcdownload.mirror.IInjector;
import net.qpowei.mcdownload.mirror.IMirror;
import net.qpowei.mcdownload.mirror.IURLPath;

public interface IProvidersSetters extends IProviders 
{
	void setMirror(IMirror mirror);
	void setInjector(IInjector injector);
	void setURLPath(IURLPath urlPath);
	void setCacher(ICacher cacher);
}
