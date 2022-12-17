package net.qpowei.mcdownload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.qpowei.mcdownload.handler.types.StringAndRules;
import net.qpowei.mcdownload.handler.adapters.RulesStringAdapter;

public class MCDConstants
{
	public static Gson GLOBAL_GSON = new GsonBuilder().setPrettyPrinting()
	.registerTypeAdapter(StringAndRules.class, new RulesStringAdapter())
	.create();
	public static int BUFFER_SIZE = 1024;
}
