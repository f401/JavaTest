package net.qpowei.filereader.mc.nbt.tags;
import java.util.ArrayList;
import net.qpowei.filereader.mc.nbt.TagTypes;

public class IntArrayTag extends BaseArrayTag<Integer>
{
	
	public IntArrayTag(String key, ArrayList<Integer> value) {
		super(key, value);
	}

	@Override
	public IntArrayTag copy() {
		ArrayList<Integer> list = new ArrayList<>(value.size());
		for (int i : value) list.add(i);
		return new IntArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.IntArray;
	}
	
}
