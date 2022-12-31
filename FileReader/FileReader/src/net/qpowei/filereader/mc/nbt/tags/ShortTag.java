package net.qpowei.filereader.mc.nbt.tags;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class ShortTag extends Tag<Short>
{
	
	public ShortTag(String key, short value) {
		super(key, value);
	}

	@Override
	public ShortTag copy() {
		return new ShortTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Short;
	}

}
