package net.qpowei.jremap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MappingsReader {
	private BufferedReader reader;

	public MappingsReader(Reader reader) {
		this(new BufferedReader(reader));
	}

	public MappingsReader(BufferedReader reader) {
		this.reader = reader;
	}

	public void readMappings() throws IOException {
		String line = null;
		while ((line = reader.readLine().trim()) != null) {
			if (line.startsWith("#")) continue;
		}
	}
}
