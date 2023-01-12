package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class LongArrayTag extends BaseArrayTag<Long>
{

	public LongArrayTag(String key, ArrayList<Long> value) {
		super(key, value);
	}
	
	@Override
	public Tag<ArrayList<Long>> copy() {
		ArrayList<Long> list = new ArrayList<Long>(value.size());
		for (long l:value) list.add(l);
		return new LongArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.LongArray;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.beginArray();
		for (long i : value) writer.value(i);
		writer.endArray();
	}
	
}
