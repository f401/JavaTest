import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {

	public MyClassVisitor(ClassVisitor visitor) {
		super(Opcodes.ASM7, visitor);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName,
			String[] interfaces) {
		System.out.println(String.format(
				"Version: %d, Access: %d, Name: %s, Signature: %s, SuperName: %s, Interfaces: %s",
				version, access, name, signature, superName, Arrays.toString(interfaces)));
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
		System.out.println(String.format("(Field)Access: %d, Name: %s, Desc: %s, Signature: %s, Value" + value,
				access,
				name, descriptor, signature, value));
		return super.visitField(access, name, descriptor, signature, value);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
			String[] exceptions) {
		System.out.println(
				String.format("(Method)Access: %d, Name: %s, Desc: %s, Signature: %s, Exceptions: %s",
						access, name, descriptor, signature, Arrays.toString(exceptions)));
		return super.visitMethod(access, name, descriptor, signature, exceptions);
	}

}
