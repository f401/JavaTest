package net.qpowei.mcdownload.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.io.InputStream;
import net.qpowei.mcdownload.MCDConstants;
import java.io.IOException;

public class SHA1Utils
{
	public static boolean compareFileAndString(File file, String sha1) {
		try {
			return getFileSha1(file).equalsIgnoreCase(sha1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean compareStreamAndSHA1(InputStream stream, String sha1) {
		try {
			return getStreamSha1(stream).equalsIgnoreCase(sha1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static String getStreamSha1(InputStream stream) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
        int len = 0;
		byte[] buffer = new byte[MCDConstants.BUFFER_SIZE];
		try {
			while ((len = stream.read(buffer)) > 0) {
				digest.update(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String hex = new BigInteger(1, digest.digest()).toString(16);
		return fixZero(hex);
	}
	
	private static String fixZero(String src) {
		//补全0
		int fixLength = 40 - src.length();
		if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                src = "0" + src;
            }
        }
		return src;
	}
	
	public static String getFileSha1(File file) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(FileUtils.readFileAsBytes(file));
		String hex = new BigInteger(1, digest.digest()).toString(16);
		return fixZero(hex);
	}
}
