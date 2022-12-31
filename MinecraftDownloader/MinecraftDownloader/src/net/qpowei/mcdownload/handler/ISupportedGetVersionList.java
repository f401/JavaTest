package net.qpowei.mcdownload.handler;

public interface ISupportedGetVersionList<T>
{
	public T[] getReleaseList();
	public T[] getSnapshotList();
	public T[] getOldAlpha();
	public T[] getOldBeta();
	
	public T getVersionByString(String name);
	
	public T[] getListByString(String needle);
}
