package net.qpowei.mcdownload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.qpowei.mcdownload.handler.adapters.RulesStringAdapter;
import net.qpowei.mcdownload.handler.types.StringAndRules;

public class MCDConstants
{
	public static Gson gson = new GsonBuilder().setPrettyPrinting()
	.registerTypeAdapter(StringAndRules.class, new RulesStringAdapter())
	.create();
	public static int BUFFER_SIZE = 1024;
	public static String defaultVersionListPath = "/opt/mc/version_manifest_v2.json";
}
