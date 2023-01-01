package net.qpowei.mcdownload.mirror.providers;

import net.qpowei.mcdownload.mirror.ICacher;
import net.qpowei.mcdownload.mirror.IInjector;
import net.qpowei.mcdownload.mirror.IMirror;
import net.qpowei.mcdownload.mirror.IURLPath;

/** provider封装了需要实现功能的类，供外界只用一个类获取到这些功能
* 而该接口就是封装类的统一规范
*/
public interface IProviders extends IMirrorProvider, IInjectorProvider, IURLPathProvider, ICacherProvider
{
	void setMirror(IMirror mirror);
	void setInjector(IInjector injector);
	void setURLPath(IURLPath urlPath);
	void setCacher(ICacher cacher);
}
