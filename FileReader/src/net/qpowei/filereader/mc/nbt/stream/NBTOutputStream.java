package net.qpowei.filereader.mc.nbt.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import net.qpowei.filereader.EndianDataOutputStream;
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

public class NBTOutputStream extends EndianDataOutputStream {

	public NBTOutputStream(OutputStream os) throws IOException {
		super(new GZIPOutputStream(os), false);
	}

	public void writeTag(Tag<?> tag) throws IOException {
		if (tag.type() == TagTypes.End)
			writeByte(TagTypes.End.getId());
		else
			writeTag(tag, false);
	}

	private void writeTag(Tag<?> tag, boolean inList) throws IOException {
		if (!inList) {
			writeByte(tag.type().getId());
			writeUTF(tag.getKey());
		}
		switch (tag.type().getId()) {
		case 1:// Byte
			writeByte(((ByteTag) tag).getValue());
			break;
		case 2:// Short
			writeShort(((ShortTag) tag).getValue());
			break;
		case 3:// Int
			writeInt(((IntTag) tag).getValue());
			break;
		case 4:// Long
			writeLong(((LongTag) tag).getValue());
			break;
		case 5:// Float
			writeFloat(((FloatTag) tag).getValue());
			break;
		case 6:// Double
			writeDouble(((DoubleTag) tag).getValue());
			break;
		case 7:// ByteArray
			ByteArrayTag bat = (ByteArrayTag) tag;
			writeInt(bat.size());
			for (int i = 0; i < bat.size(); ++i) {
				writeByte(bat.get(i));
			}
			break;
		case 8:// String
			writeUTF(((StringTag) tag).getValue());
			break;
		case 9:// List
			ListTag lt = (ListTag) tag;
			writeByte(lt.getEntryType().getId());
			writeInt(lt.size());
			for (int i = 0; i < lt.size(); ++i) {
				writeTag(lt.get(i), true);
			}
			break;
		case 10:// Compound
			CompoundTag ct = (CompoundTag) tag;
			for (int i = 0; i < ct.size(); ++i) {
				writeTag(ct.get(i), false);
			}
			writeTag(new EndTag());
			break;
		case 11:// IntArray
			IntArrayTag iat = (IntArrayTag) tag;
			writeInt(iat.size());
			for (int i = 0; i < iat.size(); ++i) {
				writeInt(iat.get(i));
			}
			break;
		case 12:// LongArray
			LongArrayTag lat = (LongArrayTag) tag;
			writeInt(lat.size());
			for (int i = 0; i < lat.size(); ++i) {
				writeLong(lat.get(i));
			}
			break;
		default:
			throw new RuntimeException("Unknow Tag type: " + tag.type().getId());
		}
	}
}
