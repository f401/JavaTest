package net.qpowei.jremap;

public class MethodDesc {
	private final String returnType;
	private final String[] params;

	public MethodDesc(String returnType, String[] params) {
		this.returnType = returnType;
		this.params = params;
	}
	
	public MethodDesc(String desc) {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (; desc.charAt(i) != ')'; ++i) {
			sb.append(desc.charAt(i));
		}
		this.params = sb.toString().split(";");
		this.returnType = sb.substring(i + 1);
	}

	public String getReturnType() {
		return returnType;
	}

	public String[] getParams() {
		return params;
	}
}
