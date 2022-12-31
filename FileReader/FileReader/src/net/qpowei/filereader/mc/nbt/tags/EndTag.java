package net.qpowei.filereader.mc.nbt.tags;

import net.qpowei.filereader.mc.nbt.TagTypes;

public class EndTag extends Tag<Byte>
{

	@Override
	public TagTypes type() {
		return TagTypes.End;
	}
	
	
	public EndTag() {
		super("", (byte)0);
	}

	@Override
	public String getKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Byte getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setKey(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValue(Byte value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EndTag copy() {
		return new EndTag();
	}

	@Override
	public String toString() {
		return "Tag_EndTag {}";
	}
	
}
