package net.qpowei.mcdownload.mirror;

public interface IInjector
{
	public String injectMainJarURL(String url);
	public String injectJsonIndexesURL(String url);
	public String injectLoggingFileURL(String url);
    public String injectLibraryURL(String url);
}
