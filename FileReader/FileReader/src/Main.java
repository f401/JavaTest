import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.qpowei.filereader.android.dex.stream.DexInputStream;
import net.qpowei.filereader.mc.nbt.stream.NBTInputStream;
import net.qpowei.filereader.mc.nbt.stream.NBTOutputStream;
import net.qpowei.filereader.mc.nbt.tags.*;

public class Main {

	public static void main(String[] args) throws IOException {
		androidDex();
	}

	public static void androidDex() throws IOException {
		DexInputStream dis = new DexInputStream(new FileInputStream("/opt/JavaTest/FileReader/exampleFiles/classes.dex"));
		System.out.println(dis.readFile());
		dis.close();
	}

	public static void mcnbt() throws FileNotFoundException, IOException {
		NBTInputStream nis = new NBTInputStream(new FileInputStream("/opt/JavaTest/FileReader/exampleFiles/ship.nbt"));
		Tag<?> tag = nis.readTag();
		System.out.println(tag);
		nis.close();
		NBTOutputStream nos = new NBTOutputStream(new FileOutputStream("/opt/JavaTest/ship.nbt"));
		nos.writeTag(tag);
		nos.close();
	}

}
