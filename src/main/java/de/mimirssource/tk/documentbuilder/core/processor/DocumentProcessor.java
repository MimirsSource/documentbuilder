package de.mimirssource.tk.documentbuilder.core.processor;

import java.util.Map;

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
	 */
	void processDocuments(final Map<String,Object> contentObjectMap);

}
