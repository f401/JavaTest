import java.util.*;
import net.qpowei.filereader.EndianDataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		EndianDataOutputStream edos = new EndianDataOutputStream(new FileOutputStream("/sdcard/test.txt"), false);
        edos.writeInt(0x123456);
		edos.close();
    }
    
}
