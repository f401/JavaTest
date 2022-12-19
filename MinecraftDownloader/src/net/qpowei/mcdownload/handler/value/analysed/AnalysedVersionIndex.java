package net.qpowei.mcdownload.handler.value.analysed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.qpowei.mcdownload.handler.types.StringAndRules;
import net.qpowei.mcdownload.handler.value.URLSizedProperties;
import net.qpowei.mcdownload.handler.value.VersionIndex;
import net.qpowei.mcdownload.mirror.providers.IProviders;
import net.qpowei.mcdownload.MCDConstants;

public class AnalysedVersionIndex implements IMirrorProperties
{
	
	public static final String MINECRAFT_SERVER = "server";
	public static final String MINECRAFT_CLIENT = "client";
	public static final String MINECRAFT_SERVER_MAPPINGS = "server_mappings";
	public static final String MINECRAFT_CLIENT_MAPPINGS = "client_mappings";
	
	/* since 1.13+ */
	private final StringAndRules[] argumentsGame, argumentsJVM;//不用封装
	
	//assets
	private final String assetsIndexName, assetsIndexURL, assetsIndexSHA1;
	private final int assetsIndexSize, assetsTotalSize, assetsComplianceLevel;
	
	private final String versionName;
	
	//java
	private final String javaComponent;
	private final int javaMajorVersion;
	
	//time
	private final String time, releaseTime;
	
	private final String mainClass, type;
	private final int minimumLauncherVersion;
	
	//logging
	private final String loggingArguments, 
	    loggingFileType, loggingFileName, loggingFileURL, loggingFileSHA1;
	private final int loggingFileSize;
	
	private final AnalysedVersionIndex.DependentLibrary[] dependsLibrary;
	private final Map<String, MinecraftMainJarJnfo> mainJar;

	
	private IProviders provider;
	
	// :(
	public AnalysedVersionIndex(StringAndRules[] argumentsGame, StringAndRules[] argumentsJVM, String assetsName, String assetsURL, String assetsSHA1, int assetsSize, int assetsTotalSize, int assetsComplianceLevel, String versionName, String javaComponent, int javaMajorVersion, String time, String releaseTime, String mainClass, String type, int minimumLauncherVersion, String loggingArguments, String loggingFileType, String loggingFileName, String loggingFileURL, String loggingFileSHA1, int loggingFileSize, AnalysedVersionIndex.DependentLibrary[] dependsLibrary, Map<String, MinecraftMainJarJnfo> mainJar) {
		this.argumentsGame = argumentsGame;
		this.argumentsJVM = argumentsJVM;
		this.assetsIndexName = assetsName;
		this.assetsIndexURL = assetsURL;
		this.assetsIndexSHA1 = assetsSHA1;
		this.assetsIndexSize = assetsSize;
		this.assetsTotalSize = assetsTotalSize;
		this.assetsComplianceLevel = assetsComplianceLevel;
		this.versionName = versionName;
		this.javaComponent = javaComponent;
		this.javaMajorVersion = javaMajorVersion;
		this.time = time;
		this.releaseTime = releaseTime;
		this.mainClass = mainClass;
		this.type = type;
		this.minimumLauncherVersion = minimumLauncherVersion;
		this.loggingArguments = loggingArguments;
		this.loggingFileType = loggingFileType;
		this.loggingFileName = loggingFileName;
		this.loggingFileURL = loggingFileURL;
		this.loggingFileSHA1 = loggingFileSHA1;
		this.loggingFileSize = loggingFileSize;
		this.dependsLibrary = dependsLibrary;
		this.mainJar = mainJar;
		
		this.provider = MCDConstants.defaultProviders;
	}

	public StringAndRules[] getArgumentsGame() {
		return argumentsGame;
	}

	public StringAndRules[] getArgumentsJVM() {
		return argumentsJVM;
	}

	public String getAssetsIndexName() {
		return assetsIndexName;
	}

	public String getAssetsIndexURL() {
		return provider.getInjector().injectJsonIndexesURL(assetsIndexURL);
	}

	public String getAssetsIndexSHA1() {
		return assetsIndexSHA1;
	}

	public int getAssetsIndexSize() {
		return assetsIndexSize;
	}

	public int getAssetsTotalSize() {
		return assetsTotalSize;
	}

	public int getAssetsComplianceLevel() {
		return assetsComplianceLevel;
	}

	public String getVersionName() {
		return versionName;
	}

	public String getJavaComponent() {
		return javaComponent;
	}

	public int getJavaMajorVersion() {
		return javaMajorVersion;
	}

	public String getTime() {
		return time;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public String getMainClass() {
		return mainClass;
	}

	public String getType() {
		return type;
	}

	public int getMinimumLauncherVersion() {
		return minimumLauncherVersion;
	}

	public String getLoggingArguments() {
		return loggingArguments;
	}

	public String getLoggingFileType() {
		return loggingFileType;
	}

	public String getLoggingFileName() {
		return loggingFileName;
	}

	public String getLoggingFileURL() {
		return provider.getInjector().injectLoggingFileURL(loggingFileURL);
	}

	public String getLoggingFileSHA1() {
		return loggingFileSHA1;
	}

	public int getLoggingFileSize() {
		return loggingFileSize;
	}

	public AnalysedVersionIndex.DependentLibrary[] getDependsLibrary() {
		return dependsLibrary;
	}

	public MinecraftMainJarJnfo getMainJar(String needle) {
		return mainJar.get(needle);
	}
	
	public static AnalysedVersionIndex analyse(VersionIndex src) {
		
		StringAndRules[] gameArgs = null, jvmArgs = null;
		
		if (src.arguments != null) {
			gameArgs = src.arguments.game;
			jvmArgs = src.arguments.jvm;
		}
		
		String loggingArguments = null,
		    loggingFileType = null, loggingFileName = null, 
			loggingFileURL = null, loggingFileSHA1 = null;
		int loggingFileSize = 0;
		
		if (src.logging != null) {
			loggingArguments = src.logging.client.arguments;
			loggingFileType = src.logging.client.type;
			loggingFileName = src.logging.client.file.id;
			loggingFileURL = src.logging.client.file.url;
			loggingFileSHA1 = src.logging.client.file.sha1;
			loggingFileSize = src.logging.client.file.size;
		}
		
		return new AnalysedVersionIndex(
		    gameArgs, jvmArgs, 
			src.assets, src.assetIndex.url, src.assetIndex.sha1,
			src.assetIndex.size, src.assetIndex.totalSize, 
			src.complianceLevel, 
			src.id, 
			src.javaVersion.component, src.javaVersion.majorVersion, 
			src.time, src.releaseTime, 
			src.mainClass, src.type, src.minimumLauncherVersion,
			loggingArguments, loggingFileType,
			loggingFileName, loggingFileURL,
			loggingFileSHA1, loggingFileSize, 
			DependentLibrary.analyse(src.libraries), MinecraftMainJarJnfo.analyse(src.downloads)
		);
	}
	
	@Override
	public IProviders getMirrorProvider() {
		return provider;
	}

	@Override
	public void setMirrorProvider(IProviders provider) {
		setMirrorProvider(provider, true);
	}
	
	public void setMirrorProvider(IProviders provider, boolean async) {
		this.provider = provider;
		if (async) {
			for (DependentLibrary entry : dependsLibrary) {
				entry.setMirrorProvider(provider);
			}
			
			for (Map.Entry<String, MinecraftMainJarJnfo> entry : mainJar.entrySet()) {
				entry.getValue().setMirrorProvider(provider);
			}
		}
	}
	
	public static class MinecraftMainJarJnfo extends AbstractMinecraftMirrorProperties {

		private final String url, sha1;
		private final int size;

		public MinecraftMainJarJnfo(String url, String sha1, int size) {
			this.url = url;
			this.sha1 = sha1;
			this.size = size;
		}
		
		@Override
		public String getURL() {
			return provider.getInjector().injectMainJarURL(url);
		}

		@Override
		public String getSha1() {
			return sha1;
		}

		public int getSize() {
			return size;
		}
		
		public static Map<String, MinecraftMainJarJnfo> analyse(Map<String, URLSizedProperties> src) {
			Map<String, MinecraftMainJarJnfo> result = new HashMap<>(4);
			for (Map.Entry<String, URLSizedProperties> entry : src.entrySet()) {
				URLSizedProperties value = entry.getValue();
				result.put(entry.getKey(), new MinecraftMainJarJnfo(
				    value.url, value.sha1, value.size));
			}
			return result;
		}
	}
	
	public static class DependentLibrary extends AbstractMinecraftMirrorProperties {

		private final String name;
		private final VersionIndex.Rules[] rules;
		private final String url, sha1;
		private final int size;

		public DependentLibrary(String name, VersionIndex.Rules[] rules, String url, String sha1, int size) {
			this.name = name;
			this.rules = rules;
			this.url = url;
			this.sha1 = sha1;
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public VersionIndex.Rules[] getRules() {
			return rules;
		}

		@Override
		public String getURL() {
			return provider.getInjector().injectLibraryURL(url);
		}

		@Override
		public String getSha1() {
			return sha1;
		}

		public int getSize() {
			return size;
		}
		
		public static AnalysedVersionIndex.DependentLibrary[] analyse(VersionIndex.DependentLibrary[] src) {
			ArrayList<AnalysedVersionIndex.DependentLibrary> result = new ArrayList<>();
			for (VersionIndex.DependentLibrary entry : src) {
				result.add(new DependentLibrary(entry.name, entry.rules, 
				    entry.downloads.artifact.url, entry.downloads.artifact.sha1, entry.downloads.artifact.size));
			}
			return result.toArray(new DependentLibrary[result.size()]);
		}
	}
	
}
