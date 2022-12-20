package net.qpowei.mcdownload.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

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
	
	public static String getFileSha1(File file) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(FileUtils.readFileAsBytes(file));
		String hex = new BigInteger(1, digest.digest()).toString(16);
		//补全0
		int fixLength = 40 - hex.length();
		if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                hex = "0" + hex;
            }
        }
		return hex;
	}
}
