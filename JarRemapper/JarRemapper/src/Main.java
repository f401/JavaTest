import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Main {
	public static void main (String[] args) throws FileNotFoundException, IOException {
		ClassReader classReader = new ClassReader(new FileInputStream("/tmp/jar/any.class"));
		ClassWriter classwWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
		MyClassVisitor visitor = new MyClassVisitor(classwWriter);
		
		classReader.accept(visitor, ClassReader.EXPAND_FRAMES);
	}
}
