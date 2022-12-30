package net.qpowei.filereader.mc.nbt.tags;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class StringTag extends Tag<String>
{
	
	public StringTag(String key, String value) {
		super(key, value);
	}

	@Override
	public StringTag copy() {
		return new StringTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.String;
	}
	
}
