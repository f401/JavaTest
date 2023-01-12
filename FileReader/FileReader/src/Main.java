import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.qpowei.filereader.android.dex.DexPrinter;
import net.qpowei.filereader.android.dex.DexReader;
import net.qpowei.filereader.android.dex.value.DexFile;
import net.qpowei.filereader.mc.nbt.TagTypes;
import net.qpowei.filereader.mc.nbt.TagAdapter;
import net.qpowei.filereader.mc.nbt.stream.NBTInputStream;
import net.qpowei.filereader.mc.nbt.stream.NBTOutputStream;
import net.qpowei.filereader.mc.nbt.tags.*;

public class Main {

	public static void main(String[] args) throws IOException {
		mcnbt();
	}

	public static void androidDex() throws IOException {
		DexReader dis = new DexReader("/opt/JavaTest/FileReader/exampleFiles/classes.dex");
		DexFile file = dis.readDexFile();
		System.out.println(file);
		System.out.println("\n");
		DexPrinter.print(file, System.out);
		dis.close();
	}

	public static void mcnbt() throws FileNotFoundException, IOException {
		NBTInputStream nis = new NBTInputStream(
				new FileInputStream("/opt/JavaTest/FileReader/exampleFiles/ship.nbt"));
		Tag<?> tag = nis.readTag();
		System.out.println(tag);

		Gson gson = TagAdapter.configGsonBuilder(new GsonBuilder().setPrettyPrinting()).create();
		System.out.println(gson.toJson(tag));

		nis.close();
		NBTOutputStream nos = new NBTOutputStream(new FileOutputStream("/opt/JavaTest/ship.nbt"));
		nos.writeTag(tag);
		nos.close();
	}

}
