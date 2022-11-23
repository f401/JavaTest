package net.qpowei.mcdownload.handler.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.qpowei.mcdownload.handler.RulesString;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import net.qpowei.mcdownload.handler.JsonParser;
import net.qpowei.mcdownload.Tools;
import net.qpowei.mcdownload.handler.value.VersionIndex;

public class RulesStringAdapter implements JsonDeserializer<RulesString>
{

	@Override
	public RulesString deserialize(JsonElement p1, Type p2, JsonDeserializationContext p3) throws JsonParseException {
		RulesString result = new RulesString();
	    if (p1.isJsonObject()) {
			result.setRule(Tools.GLOBAL_GSON.fromJson(p1.getAsJsonObject().get("rules").toString(), VersionIndex.Rules[].class));
		} else {
			result.setString(p1.getAsString());
		}
		return result;
	}
	
}
