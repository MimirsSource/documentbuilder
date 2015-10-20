package de.mimirssource.tk.documentbuilder.core.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

/**
 * An implementation of {@link de.mimirssource.tk.documentbuilder.core.io.TemplateProvider} to provide access to local files.
 * 
 * @author thomas.kloppe
 *
 */
public class FileBasedOutputProvider implements OutputProvider {

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Writer getWriter(GenerationChannel generationChannel, String name,
			Object dataObject) {
		String outputDirectoryPath = generationChannel.getOutputDirectoryPath();
		String fileExtension = generationChannel.getFileExtension();
		try {
			File file = new File(outputDirectoryPath+File.separator+name+"."+fileExtension);
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			return new FileWriter(file);
		} catch (IOException e) {
			// TODO logging
			throw new IllegalStateException("Could not write to output file "+name+"."+fileExtension+" of channel "+generationChannel.getUniqueName(), e);
		}
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void handleFinaleActions(Writer writer) {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

