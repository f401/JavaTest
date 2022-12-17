package net.qpowei.mcdownload.handler;

import net.qpowei.mcdownload.handler.constants.VersionListTypes;

public abstract class AbstractSupportedGetVersion<T> implements ISupportedGetVersionList<T> 
{
	@Override
	public T[] getReleaseList() {
		return getListByString(VersionListTypes.TYPE_RELEASE);
	}

	@Override
	public T[] getSnapshotList() {
		return getListByString(VersionListTypes.TYPE_SNAPSHOT);
	}

	@Override
	public T[] getOldAlpha() {
		return getListByString(VersionListTypes.TYPE_OLD_ALPHA);
	}

    @Override
	public T[] getOldBeta() {
		return getListByString(VersionListTypes.TYPE_OLD_BETA);
	}
}
