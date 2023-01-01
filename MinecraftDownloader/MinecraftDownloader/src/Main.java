import java.io.File;
import java.io.IOException;

import net.qpowei.mcdownload.MCDConstants;
import net.qpowei.mcdownload.MinecraftDownloader;
import net.qpowei.mcdownload.VersionProfile;
import net.qpowei.mcdownload.mirror.DefaultMirrors;
import net.qpowei.mcdownload.util.MultiFileDownloader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		MCDConstants.defaultProviders.setMirror(DefaultMirrors.BMCLAPI);
		new MinecraftDownloader(new VersionProfile("1.16.5", "1.16.5", "/sdcard/MinecraftDownloader", false), 
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
		System.out.println("download finish, using " + (double)((System.currentTimeMillis() - start)));
    }
    
}
