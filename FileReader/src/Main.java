import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.qpowei.filereader.mc.nbt.TagTypes;
import net.qpowei.filereader.mc.nbt.TagAdapter;
import net.qpowei.filereader.mc.nbt.stream.NBTInputStream;
import net.qpowei.filereader.mc.nbt.stream.NBTOutputStream;
import net.qpowei.filereader.mc.nbt.tags.*;

public class Main {

	public static void main(String[] args) throws IOException {
		mcnbt();
	}

	public static void mcnbt_gson() {
		DoubleTag dt = new DoubleTag("DoubleTag", 1.123456789);
		FloatTag ft = new FloatTag("FloatTag", 123.4f);

		ArrayList<Tag<?>> rootList = new ArrayList<>(2);
		rootList.add(dt);
		rootList.add(ft);
		
		CompoundTag root = new CompoundTag(null, rootList);

		Gson gson = TagAdapter.configGsonBuilder(new GsonBuilder().setPrettyPrinting()).create();

		String json = gson.toJson(root);
		System.out.println(json);
		System.out.println(gson.fromJson(json, Tag.class));
	}

	public static void mcnbt() throws FileNotFoundException, IOException {
		NBTInputStream nis = new NBTInputStream(
				new FileInputStream("/opt/JavaTest/FileReader/exampleFiles/ship.nbt"));
		Tag<?> tag = nis.readTag();

		Gson gson = TagAdapter.configGsonBuilder(new GsonBuilder().setPrettyPrinting()).create();
		String json = gson.toJson(tag);
		System.out.println(json);
		Tag<?> tag2 = gson.fromJson(json, Tag.class);
		System.err.println(gson.toJson(tag2));
		
		nis.close();
		NBTOutputStream nos = new NBTOutputStream(new FileOutputStream("/opt/JavaTest/ship.nbt"));
		nos.writeTag(tag);
		nos.close();
	}

}
