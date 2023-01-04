package net.qpowei.filereader.android.dex.value;

import java.util.Arrays;

public class DexCode {
	public short registersSize;//寄存器个数
	public short insSize;//参数的个数
	public short outsSize;//调用其他方法时使用的寄存器个数
	public short triesSize;//try/catch 语句个数
	public int debugInfoOff;//debug 信息的偏移量
	public int insnsSize;//指令集的个数
	public short[] insns;//指令
			     
	@Override
	public String toString() {
		return "DexCode [registersSize=" + registersSize + ", insSize=" + insSize + ", outsSize=" + outsSize
				+ ", triesSize=" + triesSize + ", debugInfoOff=" + debugInfoOff + ", insnsSize=" + insnsSize
				+ ", insns=" + Arrays.toString(insns) + "]";
	}
}
