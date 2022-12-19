package net.qpowei.mcdownload.util;

public class MultiFileDownloader
{
	private final int maxRetryTimes, maxThread;

	public MultiFileDownloader() {
		this(5, Math.max(6, Runtime.getRuntime().availableProcessors() * 2));
	}
	
	public MultiFileDownloader(int maxRetryTimes, int maxThread) {
		this.maxRetryTimes = maxRetryTimes;
		this.maxThread = maxThread;
	}
}
