import java.util.*;
import net.qpowei.filereader.EndianDataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.qpowei.filereader.mc.nbt.steam.NBTInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		mcnbt();
    }
	
	public static void mcnbt() throws FileNotFoundException, IOException {
		NBTInputStream nis = new NBTInputStream(new FileInputStream("/sdcard/base_floor"));
		System.out.println(nis.readTag());
		nis.close();
	}
    
}
