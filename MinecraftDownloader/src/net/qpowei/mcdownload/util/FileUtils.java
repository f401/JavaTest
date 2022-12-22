package net.qpowei.mcdownload.util;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import net.qpowei.mcdownload.MCDConstants;

public class FileUtils
{
	public static String fixSeparator(String needle) {
		return needle.endsWith(File.separator) ? needle : needle + File.separator;
	}
	
	public static String unixPath2windows(String src) {
		return src.replaceAll("/", "\\");
	}
	
	public static boolean makeParentDir(String needle) {
		return makeParentDir(new File(needle));
	}
	
	public static boolean makeParentDir(File needle) {
		if (needle != null) {
			File parentFile = needle.getParentFile();
			if (!parentFile.exists()) {
				return parentFile.mkdirs();
			}
			return true;
		}
		return false;
	}
	
	public static String readFileAsString(String path) {
		return readFileAsString(new File(path));
	}
	
	public static String readFileAsString(File path) {
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path));
			String tmp = "";
			while((tmp = br.readLine()) != null) {
				result.append(tmp + "\n");
			}
			result.deleteCharAt(result.length() - 1);// remove \n
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public static byte[] readFileAsBytes(File file) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int len = 0;
			byte[] buffer = new byte[MCDConstants.BUFFER_SIZE];
			while((len = fis.read(buffer)) > 0) {
				baos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}
