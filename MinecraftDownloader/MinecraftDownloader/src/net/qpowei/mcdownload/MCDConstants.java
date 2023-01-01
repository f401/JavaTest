package net.qpowei.mcdownload;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.qpowei.mcdownload.handler.adapters.RulesStringAdapter;
import net.qpowei.mcdownload.handler.types.StringAndRules;
import net.qpowei.mcdownload.mirror.DefaultProviders;
import net.qpowei.mcdownload.mirror.providers.IProviders;

public class MCDConstants
{
	public static int bestThreadCount = Math.max(6, Runtime.getRuntime().availableProcessors() * 2);
	public static ExecutorService mcdThreadPool = new ThreadPoolExecutor(0, bestThreadCount, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	public static Gson gson = new GsonBuilder().setPrettyPrinting()
	.registerTypeAdapter(StringAndRules.class, new RulesStringAdapter())
	.create();
	public static int BUFFER_SIZE = 1024;
	public static IProviders defaultProviders = DefaultProviders.create();
	public static String defaultVersionListPath = "/opt/mc/version_manifest_v2.json";
}
