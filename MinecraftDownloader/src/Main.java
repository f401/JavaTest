import java.util.*;

import net.qpowei.mcdownload.handler.JsonParser;
import net.qpowei.mcdownload.handler.value.VersionList;
import net.qpowei.mcdownload.handler.VersionListAnalyser;
import net.qpowei.mcdownload.util.DownloadUtils;
import java.io.File;
import net.qpowei.mcdownload.util.SHA1Utils;
import com.google.gson.GsonBuilder;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(JsonParser.parseVersionIndex("/storage/emulated/0/1.19.2.json").arguments.game[23]);
		System.out.println("Finish");
	    
    }
    
}
