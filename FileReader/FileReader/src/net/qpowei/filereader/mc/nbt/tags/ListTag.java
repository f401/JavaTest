package net.qpowei.filereader.mc.nbt.tags;

import java.util.ArrayList;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class ListTag extends BaseArrayTag<Tag<?>>
{

	private final TagTypes entryType;
	
	public ListTag(String key, ArrayList<Tag<?>> value, TagTypes entryType) {
		super(key, value);
		this.entryType = entryType;
		for (Tag t : value) {
			if (t.type() != entryType) {
				throw new UnsupportedOperationException("Type should be: " + entryType + ", but " + t.type() + ", toString: " + t.toString());
			}
		}
	}
	
	@Override
	public ListTag copy() {
		ArrayList<Tag<?>> list = new ArrayList<>(value.size());
		for (Tag<?> t: value) list.add(t.copy());
		return new ListTag(key, list, entryType);
	}

	public TagTypes getEntryType() {
		return entryType;
	}
	
	@Override
	public TagTypes type() {
		return TagTypes.List;
	}

	@Override
	public void add(Tag<?> t) {
		if (entryType != t.type()) {
			throw new UnsupportedOperationException("Type should be: " + entryType + ", but " + t.type() + ", toString: " + t.toString());
		}
		super.add(t);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Tag_List { key: ");
		result.append(key);
		result.append(", value: [");
		for (Tag<?> t : value) {
			result.append(", ");
			result.append(t.getValue());
		}
		result.append("]");
		return result.toString();
	}

}
