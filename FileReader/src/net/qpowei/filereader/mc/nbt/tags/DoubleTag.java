package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class DoubleTag extends Tag<Double>
{

	public DoubleTag(String key, double value) {
		super(key, value);
	}

	@Override
	public DoubleTag copy() {
		return new DoubleTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Double;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.value(value);
	}
	
}
