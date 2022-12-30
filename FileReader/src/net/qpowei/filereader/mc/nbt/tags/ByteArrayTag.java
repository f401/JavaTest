package net.qpowei.filereader.mc.nbt.tags;

import net.qpowei.filereader.mc.nbt.TagTypes;
import java.util.ArrayList;
import org.json.JSONObject;

public class ByteArrayTag extends BaseArrayTag<Byte>
{
	
	public ByteArrayTag(String key, ArrayList<Byte> value) {
		super(key, value);
	}

	@Override
	public ByteArrayTag copy() {
		ArrayList<Byte> list = new ArrayList<>(value.size());
		for (byte b : value) list.add(b);
		return new ByteArrayTag(key, list);
	}

	@Override
	public TagTypes type() {
		return TagTypes.ByteArray;
	}
	
}
