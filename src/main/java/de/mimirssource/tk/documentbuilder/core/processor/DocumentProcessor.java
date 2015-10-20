package de.mimirssource.tk.documentbuilder.core.processor;

import java.util.Map;
import java.util.Set;

import de.mimirssource.tk.documentbuilder.core.io.OutputProvider;
import de.mimirssource.tk.documentbuilder.core.io.TemplateProvider;
import de.mimirssource.tk.documentbuilder.exceptions.DocumentProcessorException;

/**
 * The DocumentProcessor iterates over several data objects with the same structure and tries to merge them with one
 * template.
 * 
 * This is not a singleton bean. It is designed to be instantiated once per {@link de.mimirssource.tk.documentbuilder.config.DocumentType}.
 * 
 * See the {@link de.mimirssource.tk.documentbuilder.context.ApplicationConfig} for configuration options.
 * 
 * @author thomas.kloppe
 *
 */
public interface DocumentProcessor {

	/**
	 * Generate a file for each element in the map.
	 * 
	 * @param contentObjectMap - POJO objects containing content data information
	 * @param documentTypeName - the name of the document type. This parameter defines the structure of the document
	 * @throws DocumentProcessorException
	 */
	void processDocuments(final Map<String,Object> contentObjectMap, final String documentTypeName ) 
			throws DocumentProcessorException;

	/**
	 * 
	 * @param contentObjectMap - POJO objects containing content data information
	 * @param documentTypeName - the name of the document type. This parameter defines the structure of the document
	 * @param channelList - a list of output channels to generate. If the list is null or empty all available channels will be generated
	 * @throws DocumentProcessorException
	 */
	void processDocuments(Map<String, Object> contentObjectMap, String documentTypeName, Set<String> channelList)
					throws DocumentProcessorException;
	
	void processDocuments(Map<String, Object> contentObjectMap, String documentTypeName, String channelName)
			throws DocumentProcessorException;
	 
	/**
	 * The stream provider applies the input and output streams to the document processor. There is already a stream provider inserted on object initialization.
	 * This method can be used to overwrite the default behavior set by the spring context.
	 * 
	 * @param streamProvider - the input and output stream provider
	 */
	void setTemplateProvider(TemplateProvider streamProvider);
	
	void setOutputProvider(OutputProvider outputProvider);

}
