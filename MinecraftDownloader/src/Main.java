import java.util.*;

import net.qpowei.mcdownload.handler.JsonParser;
import net.qpowei.mcdownload.handler.value.VersionList;
import net.qpowei.mcdownload.handler.VersionListAnalyser;
import net.qpowei.mcdownload.util.DownloadUtils;
import java.io.File;
import net.qpowei.mcdownload.util.SHA1Utils;
import com.google.gson.GsonBuilder;
import net.qpowei.mcdownload.MCDConstants;
import net.qpowei.mcdownload.mirror.DefaultMirrors;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.util.MultiFileDownloader;
import java.io.IOException;
import net.qpowei.mcdownload.MinecraftDownloader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.qpowei.mcdownload.VersionProfile;
import net.qpowei.mcdownload.handler.value.VersionIndex;
import net.qpowei.mcdownload.util.RulesUtils;

public class Main {
	
	public static void main(String[] args) throws IOException {
		MCDConstants.defaultProviders.setMirror(DefaultMirrors.BMCLAPI);
		
		new MinecraftDownloader(new VersionProfile("1.19.2", "1.19.2", "/sdcard/MinecraftDownloader", false), 
		new MultiFileDownloader(), 
		    new MultiFileDownloader.DownloadEvent() {

				@Override
				public boolean onServerReturnWrongCode(String input, File to, int code, String msg) {
					System.out.println(msg);
					return false;
				}

				@Override
				public void onDownload(String input, File to, long curr, long total, double speeding) {
					if (curr / total == 1) {
						System.out.println(input + " finished");
					}
				}

				@Override
				public void onDownloadFailed(Throwable err, String inputUrl, File to, int downloadedCount, int maxRetry) {
					err.printStackTrace();
				}
			}
			, new MultiFileDownloader.DownloadEvent() {

				@Override
				public boolean onServerReturnWrongCode(String input, File to, int code, String msg) {
					System.out.println(msg);
					return false;
				}

				@Override
				public void onDownload(String input, File to, long curr, long total, double speeding) {
					if (curr / total == 1) {
						System.out.println(to + " finished");
					}
				}

				@Override
				public void onDownloadFailed(Throwable err, String inputUrl, File to, int downloadedCount, int maxRetry) {
					err.printStackTrace();
				}
			},
			new MultiFileDownloader.DownloadEvent() {

				@Override
				public boolean onServerReturnWrongCode(String input, File to, int code, String msg) {
					System.out.println(msg);
					return false;
				}

				@Override
				public void onDownload(String input, File to, long curr, long total, double speeding) {
					if (curr / total == 1) {
						System.out.println(input + " finished");
					}
				}

				@Override
				public void onDownloadFailed(Throwable err, String inputUrl, File to, int downloadedCount, int maxRetry) {
					err.printStackTrace();
				}
			}).downloadGame().extractNatives();
		System.out.println("download finish");
    }
    
}
