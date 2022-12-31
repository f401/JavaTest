package net.qpowei.filereader.mc.nbt.steam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import net.qpowei.filereader.EndianDataInputStream;
import net.qpowei.filereader.mc.nbt.TagTypes;
import net.qpowei.filereader.mc.nbt.tags.ByteArrayTag;
import net.qpowei.filereader.mc.nbt.tags.ByteTag;
import net.qpowei.filereader.mc.nbt.tags.CompoundTag;
import net.qpowei.filereader.mc.nbt.tags.DoubleTag;
import net.qpowei.filereader.mc.nbt.tags.EndTag;
import net.qpowei.filereader.mc.nbt.tags.FloatTag;
import net.qpowei.filereader.mc.nbt.tags.IntArrayTag;
import net.qpowei.filereader.mc.nbt.tags.IntTag;
import net.qpowei.filereader.mc.nbt.tags.ListTag;
import net.qpowei.filereader.mc.nbt.tags.LongArrayTag;
import net.qpowei.filereader.mc.nbt.tags.LongTag;
import net.qpowei.filereader.mc.nbt.tags.ShortTag;
import net.qpowei.filereader.mc.nbt.tags.StringTag;
import net.qpowei.filereader.mc.nbt.tags.Tag;

public class NBTInputStream extends EndianDataInputStream
{
	public NBTInputStream(InputStream stream) {
		super(stream, false);
	}
	
	public Tag<?> readTag() throws IOException {
		byte entryKind = readByte();
		if (entryKind == TagTypes.End.getId()) return new EndTag();
		return readTag(readUTF(), entryKind);
	}
	
	private Tag<?> readTag(String key, byte tagType) throws IOException {
		switch(tagType) {
		    case 1://Byte
			    return new ByteTag(key, readByte());
			case 2://Short
			    return new ShortTag(key, readShort());
			case 3://Int
			    return new IntTag(key, readInt());
			case 4://Long
			    return new LongTag(key, readLong());
			case 5://Float
			    return new FloatTag(key, readFloat());
			case 6://Double
			    return new DoubleTag(key, readDouble());
			case 7://ByteArray
			    int byteArraySize = readInt();
				ArrayList<Byte> byteArray = new ArrayList<>(byteArraySize);
				for (int i = 0; i < byteArraySize; ++i) {
					byteArray.add(readByte());
				}
				return new ByteArrayTag(key, byteArray);
			case 8://String
		        return new StringTag(key, readUTF());
			case 9://List
			    byte entryKind = readByte();
			    int listSize = readInt();
				ArrayList<Tag<?>> listList = new ArrayList<>(listSize);
				for (int i = 0; i < listSize;++i) {
					listList.add(readTag("", entryKind));
				}
				return new ListTag(key, listList, TagTypes.getById(entryKind));
			case 10://Compound
			    Tag<?> tag = null;
				ArrayList<Tag<?>> compoundList = new ArrayList<>();
			    while((tag = readTag()).type() != TagTypes.End) {
					compoundList.add(tag);
				}
				return new CompoundTag(key, compoundList);
			case 11://IntArray
			    int intArraySize = readInt();
				ArrayList<Integer> intArrayList = new ArrayList<>(intArraySize);
				for (int i = 0;i < intArraySize; ++i) {
					intArrayList.add(readInt());
				}
				return new IntArrayTag(key, intArrayList);
			case 12://LongArray
				int longArraySize = readInt();
				ArrayList<Long> longArrayList = new ArrayList<>(longArraySize);
				for (int i = 0; i < longArraySize; ++i) {
					longArrayList.add(readLong());
				}
				return new LongArrayTag(key, longArrayList);
			default:
			    throw new RuntimeException("Unknow Tag type: " + tagType);
		}
	}
}
