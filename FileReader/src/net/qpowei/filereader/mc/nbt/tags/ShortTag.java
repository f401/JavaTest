package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class ShortTag extends Tag<Short>
{
	
	public ShortTag(String key, short value) {
		super(key, value);
	}

	@Override
	public ShortTag copy() {
		return new ShortTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Short;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.value(value);
	}

}
