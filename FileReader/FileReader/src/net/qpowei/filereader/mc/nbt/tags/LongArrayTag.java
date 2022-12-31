package net.qpowei.filereader.mc.nbt.tags;
import java.util.ArrayList;
import net.qpowei.filereader.mc.nbt.TagTypes;
import java.util.Collections;

public class LongArrayTag extends BaseArrayTag<Long>
{

	public LongArrayTag(String key, ArrayList<Long> value) {
		super(key, value);
	}
	
	@Override
	public Tag<ArrayList<Long>> copy() {
		ArrayList<Long> list = new ArrayList<Long>(value.size());
		for (long l:value) list.add(l);
		return new LongArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.LongArray;
	}
	
}
