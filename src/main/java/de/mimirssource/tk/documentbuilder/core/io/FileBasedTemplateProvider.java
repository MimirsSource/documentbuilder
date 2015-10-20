package de.mimirssource.tk.documentbuilder.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

public class FileBasedTemplateProvider implements TemplateProvider {

	/**
	 * {@inheritDoc}
	 */
	public Reader getTemplateReader(
			GenerationChannel generationChannel) {
		String templateFilePath = generationChannel.getTemplateFilePath();
		try {
			Reader reader = new FileReader(new File(templateFilePath));
			return reader;
		} catch (FileNotFoundException e) {
			// TODO logging
			throw new IllegalStateException("Could not acccess the template for channel "+generationChannel.getUniqueName()+" in "+templateFilePath, e);
		}
	}

}
