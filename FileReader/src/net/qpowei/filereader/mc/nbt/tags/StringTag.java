package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class StringTag extends Tag<String>
{
	
	public StringTag(String key, String value) {
		super(key, value);
	}

	@Override
	public StringTag copy() {
		return new StringTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.String;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.value(value);
	}
	
}
