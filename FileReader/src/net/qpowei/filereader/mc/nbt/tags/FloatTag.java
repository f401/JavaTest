package net.qpowei.filereader.mc.nbt.tags;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class FloatTag extends Tag<Float>
{
	
	public FloatTag(String key, float value) {
		super(key, value);
	}
	
	@Override
	public FloatTag copy() {
		return new FloatTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Float;
	}
	
}
