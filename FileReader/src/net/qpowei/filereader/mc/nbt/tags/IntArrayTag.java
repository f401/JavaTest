package net.qpowei.filereader.mc.nbt.tags;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class IntArrayTag extends BaseArrayTag<Integer>
{
	
	public IntArrayTag(String key, ArrayList<Integer> value) {
		super(key, value);
	}

	@Override
	public IntArrayTag copy() {
		ArrayList<Integer> list = new ArrayList<>(value.size());
		for (int i : value) list.add(i);
		return new IntArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.IntArray;
	}

	@Override
	protected void write(JsonWriter writer, boolean wk) throws IOException {
		if (wk)
			writer.name(key);
		writer.beginArray();
		for (int i : value) 
			writer.value(i);
		writer.endArray();
	}
	
}
