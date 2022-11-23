package net.qpowei.mcdownload.handler.value;

import java.util.Map;
import java.util.Arrays;
import com.google.gson.annotations.SerializedName;
import net.qpowei.mcdownload.handler.RulesString;

public class VersionIndex
{
	public Argument arguments;/* since 1.13*/
	public VersionIndex.AssetsIndex assetIndex;
	public String assets;
	public String id;
	public int complianceLevel;
	//这里存放main jar文件
	public Map<String, URLSizedProperties> downloads;
	//依赖库
	public VersionIndex.DependentLibrary[] libraries;
	public VersionIndex.JavaVersion javaVersion;
	
	public String mainClass;
	public int minimumLauncherVersion;
	public String type;//In VersionList
	
	public String time;
	public String releaseTime;
	
	public Logging logging;
	
	//与直接放在包里的类不同，直接放在包里的是指assets index(比如1.19.json)文件，
	//而这里的指的是在VersionIndex(比如1.19.2.json)中的一个元素
	public static class AssetsIndex extends URLSizedProperties {
		public String id;
		public int totalSize;

		@Override
		public String toString() {
			return String.format("AssetsIndex{id: %s, totalSize: %d, url: %s, size: %d, sha1: %s}", id, totalSize, url, size, sha1);
		}
	}
	
	public static class DependentLibrary {
		public String name;
		public LibrarayDownload downloads;
		public Rules[] rules;
		
		public static class LibrarayDownload {
			public URLSizedProperties artifact;

			@Override
			public String toString() {
				return String.format("LibraryDownload {url: %s, size: %d, sha1: %s}", artifact.url
				, artifact.size, artifact.sha1);
			}
		}

		@Override
		public String toString() {
			return String.format("DependentLibrary {name: %s, downloads: %s, rules: %s}", name, downloads, rules);
		}
		
	}
	
	public static class Logging {
		public LoggingClient client;
		public static class LoggingClient {
			public String arguments, type;
			public LoggingFile file;
			public static class LoggingFile extends URLSizedProperties {
				public String id;
			}
		}
	}
	
	public static class JavaVersion {
		public String component;
        public int majorVersion;

		@Override
		public String toString() {
			return String.format("JavaVersion {component: %s, majorVersion: %s}", component, majorVersion);
		}
		
	}
	
	public static class Argument {
		public RulesString[] game;
        public RulesString[] jvm;

		@Override
		public String toString() {
			return String.format("Argument {game: %s, jvm: %s}", Arrays.toString(game), Arrays.toString(jvm));
		}
		
	}
	
	public static class Rules {
        public String action/*allow disallow*/;
		public OS os;
		//since 1.13, in arguments
		public Features features;
		public Object[] value;//可能是数组，也可能就是个String
		
		public static class OS {
			public String name, 
			    version/*may be null*/;

			@Override
			public String toString() {
				return String.format("OS{name: %s, version: %s}", name, version);
			}
				
		}
		
		public static class Features {
			
			@SerializedName("is_demo_user")
			public boolean isDemoUser;
			@SerializedName("has_custom_resolution")
			public boolean hasCustomResolution;

			@Override
			public String toString() {
				return "Features{is_demo_user:" + isDemoUser
				+ ", has_custom_resolution:" + hasCustomResolution + "}";
			}
		}

		@Override
		public String toString() {
			return String.format("Rules {action: %s, os: %s, features: %s, value: %s}"
			, action, os, features, Arrays.toString(value));
		}
	}

	@Override
	public String toString() {
		return String.format("VersionIndex {arguments: %s, assetIndex: %s, assets: %s, id: %s, compilanceLevel: %d, downloads: %s, libraries: %s, javaVersion: %s, mainClass: %s, minimumLauncherVersion: %d, type: %s, time: %s, releaseTime: %s, logging: %s", 
		    arguments, assetIndex, assets, id, complianceLevel, downloads, libraries, javaVersion, mainClass, minimumLauncherVersion, type, time, releaseTime, logging);
	}
	
}
