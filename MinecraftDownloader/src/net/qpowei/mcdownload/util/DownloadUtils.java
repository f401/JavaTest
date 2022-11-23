package net.qpowei.mcdownload.util;

import java.io.File;
import java.net.URL;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.io.FileOutputStream;
import net.qpowei.mcdownload.Tools;

public class DownloadUtils
{
	
	public static String USER_AGENT = "DOWNLOADER";
	
	public static void download(String url, OutputStream os) {
		try {
			download(new URL(url), os);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void download(URL url, OutputStream os) {
		InputStream is = null;
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setConnectTimeout(15000);
			http.setDoInput(true);
			http.setRequestProperty("User-Agent", USER_AGENT);
			http.connect();
			
			if (http.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Http respone code error: " + http.getResponseCode());
			}
			
			is = http.getInputStream();
			
			byte[] buffer = new byte[Tools.BUFFER_SIZE];
			int len = 0;
			while((len = is.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
		    
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void downloadFile(String url, File saveTo) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveTo);
		    download(url, fos);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
