package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;
 
public class IntTag extends Tag<Integer>
{
	
	public IntTag(String key, int value) {
		super(key, value);
	}

	@Override
	public IntTag copy() {
		return new IntTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Int;
	}

	@Override
	protected void write(JsonWriter writer, boolean wk) throws IOException {
		if (wk)
			writer.name(key);
		writer.value(value);
	}
	
}
