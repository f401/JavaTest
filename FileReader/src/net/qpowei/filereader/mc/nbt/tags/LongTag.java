package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class LongTag extends Tag<Long>
{
	
	public LongTag(String key, long value) {
		super(key, value);
	}

	@Override
	public LongTag copy() {
		return new LongTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Long;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.value(value);
	}
	
}
