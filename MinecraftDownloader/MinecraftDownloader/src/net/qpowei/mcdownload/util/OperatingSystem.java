package net.qpowei.mcdownload.util;

public class OperatingSystem
{
	public static final String CURRENT_OS = System.getProperty("os.name").toLowerCase();
	
	public static final String LINUX = "linux";
	public static final String WINDOWS = "windows";
	public static final String OSX = "osx";
	
	public static boolean isLinux() {
		return LINUX.equalsIgnoreCase(CURRENT_OS) || 
		    CURRENT_OS.contains(LINUX);
	}
	
	public static boolean isOsx() {
		return (OSX.equalsIgnoreCase(CURRENT_OS)||"mac".equalsIgnoreCase(CURRENT_OS))
		    || (CURRENT_OS.contains(OSX)||CURRENT_OS.contains("mac"));
	}
	
	public static boolean isWindows() {
		return (WINDOWS.equalsIgnoreCase(CURRENT_OS) || "win".equalsIgnoreCase(CURRENT_OS)) || 
		    (CURRENT_OS.contains(WINDOWS)||CURRENT_OS.contains("win"));
	}
}
