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

public class Main {
	
	public static void main(String[] args) {
		MCDConstants.defaultProviders.setMirror(DefaultMirrors.BMCLAPI);
		
		System.out.println(JsonParser.parseVersionIndex("/storage/emulated/0/1.19.2.json").analyse().getMainJar(AnalysedVersionIndex.MINECRAFT_CLIENT).getSha1());
		System.out.println(JsonParser.parseAssetsIndex("/storage/emulated/0/1.19.json").analyse().get(0).getURL());
		System.out.println("Finish");
	    
    }
    
}
