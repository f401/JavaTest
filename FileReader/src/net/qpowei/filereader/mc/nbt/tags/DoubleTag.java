package net.qpowei.filereader.mc.nbt.tags;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class DoubleTag extends Tag<Double>
{

	public DoubleTag(String key, double value) {
		super(key, value);
	}

	@Override
	public DoubleTag copy() {
		return new DoubleTag(key, value);
	}

	@Override
	public TagTypes type() {
		return TagTypes.Double;
	}
	
}
