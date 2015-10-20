package de.mimirssource.tk.documentbuilder.core.io;

import java.io.Writer;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

public interface OutputProvider {
	
	/**
	 * Provides the output stream for one file and one output channel.
	 * The information in the method parameters is used to determine a unique place for the output.
	 * 
	 * @param generationChannel - the configuration of the generation channel
	 * @param name - a name for the data object.
	 * @param dataObject - the data that is to be merged into this output
	 * @return the stream to wirte the merged data to.
	 */
	Writer getWriter(GenerationChannel generationChannel,
			String name, Object dataObject);
	
	/**
	 * Make sure that all of the data is written to the designated places and the streams are closed properly.
	 * This should always be called at the end of each work on the provided streams instead of closing them manually.
	 */
	void handleFinaleActions(Writer writer);

}
