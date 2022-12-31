package net.qpowei.mcdownload.handler.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.qpowei.mcdownload.handler.types.StringAndRules;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import net.qpowei.mcdownload.handler.JsonParser;
import net.qpowei.mcdownload.MCDConstants;
import net.qpowei.mcdownload.handler.value.VersionIndex;
import net.qpowei.mcdownload.handler.types.StringAndArray;

public class RulesStringAdapter implements JsonDeserializer<StringAndRules>
{

	@Override
	public StringAndRules deserialize(JsonElement p1, Type p2, JsonDeserializationContext p3) throws JsonParseException {
		StringAndRules result = new StringAndRules();
	    if (p1.isJsonObject()) {
			JsonObject obj = p1.getAsJsonObject();
			
			result.setRule(MCDConstants.GLOBAL_GSON.fromJson(obj.get("rules"), VersionIndex.Rules[].class));
			if (obj.has("value")) {
				result.setValue(sloveValue(obj.get("value")));
			}
		} else {
			result.setString(p1.getAsString());
		}
		return result;
	}
	
	private StringAndArray<String> sloveValue(JsonElement element) {
		StringAndArray<String> result = new StringAndArray<String>();
		if (element.isJsonPrimitive()) {
			result.setString(element.getAsString());
		} else if (element.isJsonArray()) {
			result.setArray(MCDConstants.GLOBAL_GSON.fromJson(element, String[].class));
		} else {
			throw new RuntimeException("Unknow json " + element);
		}
        return result;
	}
	
}
