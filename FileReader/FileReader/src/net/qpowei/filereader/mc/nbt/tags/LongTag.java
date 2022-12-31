package net.qpowei.filereader.mc.nbt.tags;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class LongTag extends Tag<Long>
{
	
	public LongTag(String key, long value) {
		super(key, value);
	}

	@Override
	public LongTag copy() {
		return new LongTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Long;
	}
	
}
