package net.qpowei.mcdownload.mirror.providers;

/** provider封装了需要实现功能的类，供外界只用一个类获取到这些功能
* 而该接口就是封装类的统一规范
*/
public interface IProviders extends IMirrorProvider, IInjectorProvider, IURLPathProvider, ICacherProvider
{
	
}
