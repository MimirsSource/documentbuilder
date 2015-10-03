package de.mimirssource.tk.documentbuilder.core.processor.streams;

import java.io.InputStream;
import java.io.OutputStream;

import de.mimirssource.tk.documentbuilder.config.GenerationChannel;

/**
 * To use a generic and flexible way to work with input an output the core application of the document builder works with streams. 
 * The application does not care where the data really comes from.
 * 
 * The StreamProviders responsibility is to create the streams on the basis of the configured IO mechanism. If you want to create an own
 * way to provide data this is the interface you should implement.
 * 
 * @author thomas.kloppe
 *
 */
public interface StreamProvider {

	/**
	 * Provides the output stream for one file and one output channel.
	 * The information in the method parameters is used to determine a unique place for the output.
	 * 
	 * @param generationChannel - the configuration of the generation channel
	 * @param name - a name for the data object.
	 * @param dataObject - the data that is to be merged into this output
	 * @return the stream to wirte the merged data to.
	 */
	OutputStream getOutputStream(GenerationChannel generationChannel,
			String name, Object dataObject);
	
	/**
	 * Creates an input stream to provide the template based on the channel configuration.
	 * 
	 * @param generationChannel - the configuration of the generation channel
	 * @return the input stream providing the template
	 */
	InputStream getTemplateInputStream(GenerationChannel generationChannel);

}
