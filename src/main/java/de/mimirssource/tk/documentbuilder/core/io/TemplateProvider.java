package de.mimirssource.tk.documentbuilder.core.io;

import java.io.Reader;

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
public interface TemplateProvider {

	/**
	 * Creates an input stream to provide the template based on the channel configuration.
	 * 
	 * @param generationChannel - the configuration of the generation channel
	 * @return the input stream providing the template
	 */
	Reader getTemplateReader(GenerationChannel generationChannel);
	


}
