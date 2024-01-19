package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class FloatTag extends Tag<Float>
{
	
	public FloatTag(String key, float value) {
		super(key, value);
	}
	
	@Override
	public FloatTag copy() {
		return new FloatTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Float;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.value(value);
	}
	
}
