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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag<?> other = (Tag<?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
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
