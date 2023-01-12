package net.qpowei.filereader.mc.nbt.tags;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import net.qpowei.filereader.mc.nbt.TagTypes;

public abstract class Tag<T>
{
	protected String key;
	protected T value;

	public Tag(String key, T value) {
		this.key = key;
		this.value = value;
	}


	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Tag_" + type().getDisplayName() +" [key=" + key + ", value=" + value + "]";
	}
	
	public abstract Tag<T> copy();
	
	public abstract TagTypes type();

	public void write(JsonWriter writer) throws IOException {
		write(writer, true);
	}

	protected abstract void write(JsonWriter writer, boolean writeKey) throws IOException;
	
}
