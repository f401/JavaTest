package net.qpowei.mcdownload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.qpowei.mcdownload.MCDConstants;

public class MultiFileDownloader
{
	private int maxRetryTimes, retryAfterMs, connectionTimeout;
	private DownloadEvent event;
	private ConcurrentMap<String, String> properties;
    private ConcurrentMap<String, File> downloadList;
	private ExecutorService threadPool;
	
	public MultiFileDownloader(int maxRetryTimes, int retryAfterMs, int maxThread, int connectionTimeout, MultiFileDownloader.DownloadEvent event, ConcurrentMap<String, String> header) {
		this.maxRetryTimes = maxRetryTimes;
		this.retryAfterMs = retryAfterMs;
		this.connectionTimeout = connectionTimeout;
		this.event = event;
		this.properties = header;
		
		this.downloadList = new ConcurrentHashMap<>();
		this.threadPool = new ThreadPoolExecutor(maxThread, maxThread, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	public MultiFileDownloader(DownloadEvent event) {
		this(5, 2000, MCDConstants.bestThreadCount, 10000, event, null);
	}
	
	public MultiFileDownloader() {
		this(null);
	}
	
	public void downloadAsFile(String input, File to) throws MalformedURLException, IOException {
		URL url = new URL(input);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(connectionTimeout);
		connection.setDoInput(true);
		if (properties != null) {
		    for (Map.Entry<String, String> entry : properties.entrySet()) {
		         connection.setRequestProperty(entry.getKey(), entry.getValue());
		    }
		}
		connection.connect();
		
		if (connection.getResponseCode() != 200)  {
			if (this.event == null)
			    throw new RuntimeException("HTTP respone code " + connection.getResponseCode() + ", message: " + connection.getResponseMessage());
			if (!this.event.onServerReturnWrongCode(input, to, connection.getResponseCode(), connection.getResponseMessage())) 
				return;
		}
		
	    FileUtils.makeParentDir(to);
		
		InputStream urlStream = connection.getInputStream();
		FileOutputStream fos = new FileOutputStream(to);
		byte[] buffer = new byte[MCDConstants.BUFFER_SIZE];
		
		int size = 0;
		long totalSize = connection.getContentLength(), downloadedSize = 0, start = System.currentTimeMillis();
		
		while ((size = urlStream.read(buffer)) > 0) {
			downloadedSize += size;
			if (event != null) {
				event.onDownload(input, to, downloadedSize, totalSize, downloadedSize / (double)((System.currentTimeMillis() - start)/1000));
			}
			fos.write(buffer, 0, size);
		}
		fos.close();
		urlStream.close();
		connection.disconnect();
	}
	
	public boolean downloadAsFileWithRetries(String input, File to) {
		int downloadedCount = -1;
		while(++downloadedCount < maxRetryTimes) {
		    try {
			    downloadAsFile(input, to);
				return true;
	    	} catch(Throwable e) {
			    if (event != null) {
					event.onDownloadFailed(e, input, to, downloadedCount, maxRetryTimes);
				}
				try {
					Thread.sleep(retryAfterMs);
				} catch (InterruptedException ee) {}
		    }
		}
		return false;
	}
	
	public void startMultiThreadDownloadNonBlocking() {
		MCDConstants.mcdThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					startMultiThreadDownloadBlocking();
				}
			});
	}
	
	public boolean downloadFinished() {
		return downloadList.isEmpty();
	}
	
	public void waitUntilDownloadFinish() {
		while (!downloadFinished()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
	}
	
	public void startMultiThreadDownloadBlocking() {
	    for (final ConcurrentMap.Entry<String, File> entry : downloadList.entrySet()) {
			threadPool.execute(new Runnable() {
					@Override
					public void run() {
					     downloadAsFileWithRetries(entry.getKey(), entry.getValue());
						 downloadList.remove(entry.getKey());
					}
				});
		}
	}
	
	public void addIntoDownloadList(String url, String saveTo) {
		addIntoDownloadList(url, new File(saveTo));
	}
	
	public void addIntoDownloadList(String url, File saveTo) {
        downloadList.put(url, saveTo);
	}
	
	public File removeFromDownloadList(String url) {
		return downloadList.remove(url);
	}
	
	public ConcurrentMap<String, File> getUndoneDownloadList() {
		return downloadList;
	}
	
	public interface DownloadEvent {
		//返回true表示继续下载
		boolean onServerReturnWrongCode(String input, File to, int code, String msg);
		void onDownload(String input, File to, long curr, long total, double speeding);
		void onDownloadFailed(Throwable err, String inputUrl, File to, int downloadedCount, int maxRetry);
	}
	
	public void stopDownloadNow() {
		threadPool.shutdownNow();
	}
	
	public void setMaxRetryTimes(int maxRetryTimes) {
		this.maxRetryTimes = maxRetryTimes;
	}

	public int getMaxRetryTimes() {
		return maxRetryTimes;
	}

	public void setRetryAfterMs(int retryAfterMs) {
		this.retryAfterMs = retryAfterMs;
	}

	public int getRetryAfterMs() {
		return retryAfterMs;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setEvent(DownloadEvent event) {
		this.event = event;
	}
	
	public DownloadEvent getEvent() {
		return event;
	}
	
	public ConcurrentMap<String, String> getRequestHeader() {
		return properties;
	}
	
	public void setRequestHeader(ConcurrentMap<String, String> header) {
		this.properties = header;
	}
	
}
