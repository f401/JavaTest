package net.qpowei.mcdownload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.qpowei.mcdownload.handler.RulesString;
import net.qpowei.mcdownload.handler.adapters.RulesStringAdapter;

public class Tools
{
	public static final Gson GLOBAL_GSON = new GsonBuilder().setPrettyPrinting()
	.registerTypeAdapter(RulesString.class, new RulesStringAdapter()).create();
	public static final int BUFFER_SIZE = 1024;
}
