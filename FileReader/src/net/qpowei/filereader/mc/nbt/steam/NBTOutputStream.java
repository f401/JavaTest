package net.qpowei.filereader.mc.nbt.steam;

import java.io.OutputStream;
import net.qpowei.filereader.EndianDataOutputStream;
import net.qpowei.filereader.mc.nbt.tags.Tag;
import java.io.IOException;
import net.qpowei.filereader.mc.nbt.tags.ByteArrayTag;
import net.qpowei.filereader.mc.nbt.tags.ListTag;
import net.qpowei.filereader.mc.nbt.TagTypes;
import net.qpowei.filereader.mc.nbt.tags.EndTag;

public class NBTOutputStream extends EndianDataOutputStream
{
	public NBTOutputStream(OutputStream os) {
		super(os, false);
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
		switch(tag.type().getId()) {
		    case 1://Byte
			    writeByte(tag.getValue());
				break;
			case 2://Short
			    writeShort(tag.getValue());
				break;
			case 3://Int
			    writeInt(tag.getValue());
				break;
			case 4://Long
			    writeLong(tag.getValue());
				break;
			case 5://Float
			    writeFloat(tag.getValue());
				break;
			case 6://Double
			    writeDouble(tag.getValue());
				break;
			case 7://ByteArray
			    ByteArrayTag bat = (ByteArrayTag) tag;
			    writeInt(bat.size());
				for (int i = 0; i < bat.size(); ++i) {
					writeByte(bat.get(i));
				}
				break;
			case 8://String
		        writeUTF((String)tag.getValue());
				break;
			case 9://List
			    ListTag lt = (ListTag) tag;
				writeByte(lt.getEntryType().getId());
				writeInt(lt.size());
				for (int i = 0; i < lt.size(); ++i) {
				    writeTag(lt.get(i), true);
				}
				break;
			case 10://Compound
			    writeTag(new EndTag());
				break;
			case 11://IntArray
			    
			case 12://LongArray
			default:
			    throw new RuntimeException("Unknow Tag type: " + tagType);
		}
	}
}
