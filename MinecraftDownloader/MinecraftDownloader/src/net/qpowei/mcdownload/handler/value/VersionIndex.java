package net.qpowei.mcdownload.handler.value;

import java.util.Arrays;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import net.qpowei.mcdownload.handler.types.StringAndRules;
import net.qpowei.mcdownload.handler.value.analysed.AnalysedVersionIndex;
import net.qpowei.mcdownload.mirror.providers.IProviders;

public class VersionIndex {
	// 与直接放在包里的类不同，直接放在包里的是指assets index(比如1.19.json)文件，
	// 而这里的指的是在VersionIndex(比如1.19.2.json)中的一个元素
	public static class AssetsIndex {
		public String id;
		public int totalSize;

		public int size;

		public String url;
		public String sha1;

		@Override
		public String toString() {
			return "AssetsIndex [id=" + id + ", totalSize=" + totalSize + ", size=" + size + ", url=" + url
					+ ", sha1=" + sha1 + "]";
		}
	}

	public static class DependentLibrary {
		public static class LibraryDownload {

			public static class Artifact extends URLSizedProperties {
				public String path;
			}

			public Artifact artifact;

			public Map<String, Artifact> classifiers;

			@Override
			public String toString() {
				return String.format("LibraryDownload {url: %s, size: %d, sha1: %s}", artifact.url,
						artifact.size, artifact.sha1);
			}
		}

		public static class Extract {
			public String[] exclude;

			@Override
			public String toString() {
				return "Extract [exclude=" + Arrays.toString(exclude) + "]";
			}
		}

		public static class Natives {
			public String linux;
			public String windows;
			public String osx;

			@Override
			public String toString() {
				return "Natives [linux=" + linux + ", windows=" + windows + ", osx=" + osx + "]";
			}
		}

		public String name;
		public LibraryDownload downloads;

		public Rules[] rules;

		public Extract extract;

		public Natives natives;

		@Override
		public String toString() {
			return "DependentLibrary [name=" + name + ", downloads=" + downloads + ", rules=" + Arrays.toString(rules)
					+ ", extract=" + extract + ", natives=" + natives + "]";
		}

	}

	public static class Logging {
		public static class LoggingClient {
			public static class LoggingFile extends URLSizedProperties {
				public String id;
			}

			public String arguments, type;
			public LoggingFile file;
		}

		@Override
		public String toString() {
			return "Logging [client=" + client + "]";
		}

		public LoggingClient client;
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
		public StringAndRules[] game;
		public StringAndRules[] jvm;

		@Override
		public String toString() {
			return String.format("Argument {game: %s, jvm: %s}", Arrays.toString(game),
					Arrays.toString(jvm));
		}

	}

	public static class Rules {

		public static class OS {
			public String name, version/* may be null */;

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
				return "Features{is_demo_user:" + isDemoUser + ", has_custom_resolution:"
						+ hasCustomResolution + "}";
			}
		}

		public String action/* allow disallow */;

		public OS os;

		// since 1.13, in arguments
		public Features features;

		@Override
		public String toString() {
			return String.format("Rules {action: %s, os: %s, features: %s}", action, os, features);
		}
	}

	public Argument arguments;/* since 1.13 */
	public VersionIndex.AssetsIndex assetIndex;

	public String assets;
	public String id;// version name
	public int complianceLevel;

	// 这里存放main jar文件
	public Map<String, URLSizedProperties> downloads;
	// 依赖库
	public VersionIndex.DependentLibrary[] libraries;

	public VersionIndex.JavaVersion javaVersion;

	public String mainClass;

	public int minimumLauncherVersion;

	public String type;// In VersionList

	public String time;

	public String releaseTime;

	public Logging logging;

	@Override
	public String toString() {
		return "VersionIndex [arguments=" + arguments + ", assetIndex=" + assetIndex + ", assets=" + assets + ", id="
				+ id + ", complianceLevel=" + complianceLevel + ", downloads=" + downloads + ", libraries="
				+ Arrays.toString(libraries) + ", javaVersion=" + javaVersion + ", mainClass=" + mainClass
				+ ", minimumLauncherVersion=" + minimumLauncherVersion + ", type=" + type + ", time=" + time
				+ ", releaseTime=" + releaseTime + ", logging=" + logging + "]";
	}

	public AnalysedVersionIndex analyse(IProviders provider) {
		return AnalysedVersionIndex.analyse(this, provider);
	}

}
