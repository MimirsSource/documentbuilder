package de.mimirssource.tk.documentbuilder.core.processor.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

/**
 * An implementation of {@link de.mimirssource.tk.documentbuilder.core.processor.streams.StreamProvider} to provide access to local files.
 * 
 * @author thomas.kloppe
 *
 */
public class DefaultFileStreamProvider implements StreamProvider {

	/**
	 * {@inheritDoc}
	 */
	public OutputStream getOutputStream(final GenerationChannel generationChannel, String name, Object dataObject) {
		String outputDirectoryPath = generationChannel.getOutputDirectoryPath();
		String fileExtension = generationChannel.getFileExtension();
		try {
			File file = new File(outputDirectoryPath+File.separator+name+"."+fileExtension);
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			return new FileOutputStream(file);
		} catch (IOException e) {
			// TODO logging
			throw new IllegalStateException("Could not write to output file "+name+"."+fileExtension+" of channel "+generationChannel.getUniqueName(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public InputStream getTemplateInputStream(
			GenerationChannel generationChannel) {
		String templateFilePath = generationChannel.getTemplateFilePath();
		try {
			return new FileInputStream(new File(templateFilePath));
		} catch (FileNotFoundException e) {
			// TODO logging
			throw new IllegalStateException("Could not acccess the template for channel "+generationChannel.getUniqueName()+" in "+templateFilePath, e);
		}
	}

}

