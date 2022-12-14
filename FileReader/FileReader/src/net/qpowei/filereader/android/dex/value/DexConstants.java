package net.qpowei.filereader.android.dex.value;

public class DexConstants {
	public static final byte[] MAGIC_NUMBER = { 0x64, 0x65, 0x78, 0x0A, 0x30, 0x33, 0x35, 0x00 };

	public final static int ACC_PUBLIC = 0x00000001, ACC_PRIVATE = 0x00000002, // field, method, ic
			ACC_PROTECTED = 0x00000004, // field, method, ic
			ACC_STATIC = 0x00000008, // field, method, ic
			ACC_FINAL = 0x00000010, // class, field, method, ic
			ACC_SYNCHRONIZED = 0x00000020, // method (only allowed on natives)
			ACC_SUPER = 0x00000020, // class (not used in Dalvik)
			ACC_VOLATILE = 0x00000040, // field
			ACC_BRIDGE = 0x00000040, // method (1.5)
			ACC_TRANSIENT = 0x00000080, // field
			ACC_VARARGS = 0x00000080, // method (1.5)
			ACC_NATIVE = 0x00000100, // method
			ACC_INTERFACE = 0x00000200, // class, ic
			ACC_ABSTRACT = 0x00000400, // class, method, ic
			ACC_STRICT = 0x00000800, // method
			ACC_SYNTHETIC = 0x00001000, // field, method, ic
			ACC_ANNOTATION = 0x00002000, // class, ic (1.5)
			ACC_ENUM = 0x00004000, // class, field, ic (1.5)
			ACC_CONSTRUCTOR = 0x00010000, // method (Dalvik only)
			ACC_DECLARED_SYNCHRONIZED = 0x00020000, // method (Dalvik only)
			ACC_CLASS_MASK = (ACC_PUBLIC | ACC_FINAL | ACC_INTERFACE | ACC_ABSTRACT | ACC_SYNTHETIC
					| ACC_ANNOTATION | ACC_ENUM),
			ACC_INNER_CLASS_MASK = (ACC_CLASS_MASK | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC),
			ACC_FIELD_MASK = (ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL
					| ACC_VOLATILE | ACC_TRANSIENT | ACC_SYNTHETIC | ACC_ENUM),
			ACC_METHOD_MASK = (ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL
					| ACC_SYNCHRONIZED | ACC_BRIDGE | ACC_VARARGS | ACC_NATIVE | ACC_ABSTRACT
					| ACC_STRICT | ACC_SYNTHETIC | ACC_CONSTRUCTOR | ACC_DECLARED_SYNCHRONIZED);
}
