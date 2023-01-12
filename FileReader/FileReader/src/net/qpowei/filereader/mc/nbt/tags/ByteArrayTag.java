package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class ByteArrayTag extends BaseArrayTag<Byte>
{
	
	public ByteArrayTag(String key, ArrayList<Byte> value) {
		super(key, value);
	}

	@Override
	public ByteArrayTag copy() {
		ArrayList<Byte> list = new ArrayList<>(value.size());
		for (byte b : value) list.add(b);
		return new ByteArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.ByteArray;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey)
			writer.name(key);
		writer.beginArray();
		for (byte b : value) {
			writer.value(b);
		}
		writer.endArray();
	}
	
}
