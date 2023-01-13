package net.qpowei.filereader.mc.nbt.tags;

import java.util.ArrayList;

public abstract class BaseArrayTag<T> extends Tag<ArrayList<T>> {

	public BaseArrayTag(String key, ArrayList<T> value) {
		super(key, value == null ? new ArrayList<T>() : value);
	}

	public boolean has(T t) {
		return value.contains(t);
	}

	public T get(int index) {
		return value.get(index);
	}

	public void add(T t) {
		value.add(t);
	}

	public int size() {
		return value.size();
	}

	@Override
	public String toString() {
		return "Tag_" + type().getDisplayName() + "{" + "key: " + key + ", " +
				value
				+ "}";
	}

}
