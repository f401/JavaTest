package net.qpowei.filereader.mc.nbt;

import net.qpowei.filereader.mc.nbt.tags.*;

import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TagAdapter extends TypeAdapter<Tag<?>> {

	private TagAdapter() {}

	@Override
	public Tag<?> read(JsonReader arg0) throws IOException {
		return null;
	}

	@Override
	public void write(JsonWriter arg0, Tag<?> arg1) throws IOException {
		arg1.write(arg0);
	}

	public static GsonBuilder configGsonBuilder(GsonBuilder src) {
		return src.registerTypeHierarchyAdapter(Tag.class, new TagAdapter()).serializeNulls();
	}

}
