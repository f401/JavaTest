package net.qpowei.filereader.android.dex.section;

import java.io.IOException;

import net.qpowei.filereader.EndianRandomAccessFile;

public abstract class BaseSection {
	public abstract void read(EndianRandomAccessFile input) throws IOException;
}
