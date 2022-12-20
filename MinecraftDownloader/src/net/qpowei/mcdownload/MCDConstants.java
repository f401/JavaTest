package net.qpowei.mcdownload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.qpowei.mcdownload.handler.adapters.RulesStringAdapter;
import net.qpowei.mcdownload.handler.types.StringAndRules;
import net.qpowei.mcdownload.mirror.DefaultProviders;

public class MCDConstants
{
	public static int bestThreadCount = Math.max(6, Runtime.getRuntime().availableProcessors() * 2);
	public static ExecutorService mcdThreadPool = new ThreadPoolExecutor(0, bestThreadCount, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	public static Gson GLOBAL_GSON = new GsonBuilder().setPrettyPrinting()
	.registerTypeAdapter(StringAndRules.class, new RulesStringAdapter())
	.create();
	public static int BUFFER_SIZE = 1024;
	public static DefaultProviders defaultProviders = DefaultProviders.create();
	public static String defaultVersionListPath = "/sdcard/version_manifest_v2.json";
}
