package net.qpowei.filereader.mc.nbt.tags;

import java.util.ArrayList;
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
	
}
