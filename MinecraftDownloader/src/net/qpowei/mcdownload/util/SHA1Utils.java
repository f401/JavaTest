package net.qpowei.mcdownload.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class SHA1Utils
{
	public static boolean compareFileAndString(File file, String sha1) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(FileUtils.readFileAsBytes(file));
			String hex = new BigInteger(1, digest.digest()).toString(16);
			System.out.println(hex);
			return hex.equalsIgnoreCase(sha1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return true;
	}
}
