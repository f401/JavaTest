package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class ByteTag extends Tag<Byte>
{

	public ByteTag(String key, byte value) {
		super(key, value);
	}
	
	@Override
	public ByteTag copy() {
		return new ByteTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Byte;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey) 
			writer.name(key);
		writer.value(value);
	}
	
}
