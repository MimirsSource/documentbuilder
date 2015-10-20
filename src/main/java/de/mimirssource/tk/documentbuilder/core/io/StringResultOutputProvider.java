package de.mimirssource.tk.documentbuilder.core.io;

import java.io.StringWriter;
import java.io.Writer;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

public abstract class StringResultOutputProvider implements OutputProvider {

	@Override
	public Writer getWriter(GenerationChannel generationChannel, String name,
			Object dataObject) {
		StringWriter writer = new StringWriter();
		return writer;
	}

}
