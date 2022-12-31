package net.qpowei.filereader.mc.nbt.tags;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class ByteTag extends Tag<Byte>
{

	public ByteTag(String key, byte value) {
		super(key, value);
	}
	
	@Override
	public ByteTag copy() {
		return new ByteTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Byte;
	}
	
}
