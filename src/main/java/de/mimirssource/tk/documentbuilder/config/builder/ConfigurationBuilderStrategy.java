package de.mimirssource.tk.documentbuilder.config.builder;

import java.util.Map;

import de.mimirssource.tk.documentbuilder.config.DocumentType;

/**
 * Build a object based structure from the user configured properties.
 * The builder strategy also provides default values and therefore follows a convention over configuration strategy.
 * 
 * @author thomas.kloppe
 *
 */
public interface ConfigurationBuilderStrategy {
	
	/**
	 * @return - the structure of the document types and their output channels
	 */
	Map<String,DocumentType> getDocumentTypeStructure();

}
