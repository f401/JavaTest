package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class CompoundTag extends BaseArrayTag<Tag<?>>
{

	public CompoundTag(String key, ArrayList<Tag<?>> value) {
		super(key, value);
	}
	
	@Override
	public CompoundTag copy() {
		ArrayList<Tag<?>> list = new ArrayList<>(value.size());
		for (Tag<?> t: value) list.add(t.copy());
		return new CompoundTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Compound;
	}

	@Override
	protected void write(JsonWriter writer, boolean writeKey) throws IOException {
		if (writeKey && key != null && !key.equals("")) 
			writer.name(key);
		writer.beginObject();
		for (Tag<?> tag : value) {
			if (tag != null) {
				tag.write(writer);	
			}
		}
		writer.endObject();
	}
	
}
