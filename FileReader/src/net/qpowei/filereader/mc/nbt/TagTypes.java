package net.qpowei.filereader.mc.nbt;

public enum TagTypes
{
	End(0, "End"), 
	Byte(1, "Byte"),
	Short(2, "Short"), 
	Int(3, "Int"), 
	Long(4, "Long"), 
	Float(5, "Float"), 
	Double(6, "Double"), 
	ByteArray(7, "ByteArray"), 
	String(8, "String"),
	List(9, "List"), 
	Compound(10, "Compound"), 
	IntArray(11, "IntArray"), 
	LongArray(12, "LongArray");
	
     private final int id;
	 private final String displayName;
	 
	 private TagTypes(int id, String displayName) {
		 this.id = id;
		 this.displayName = displayName;
	 }
	 
	 public int getId() {
		 return id;
	 }
	 
	 public String getDisplayName() {
		 return displayName;
	 }
	 
	 public static TagTypes getById(byte id) {
		 switch(id) {
			 case 0:
			     return End;
			 case 1://Byte
				 return Byte;
			 case 2://Short
				 return Short;
			 case 3://Int
				 return Int;
			 case 4://Long
				 return Long;
			 case 5://Float
				 return Float;
			 case 6://Double
				 return Double;
			 case 7://ByteArray
				 return ByteArray;
			 case 8://String
				 return String;
			 case 9://List
				 return List;
			 case 10://Compound
			     return Compound;
			 case 11://IntArray
			 	return IntArray;
			 case 12://LongArray
			 	return LongArray;
			 default:
				 throw new RuntimeException("Unknow Tag type: " + id);
		 }
	 }
}
