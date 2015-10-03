package de.mimirssource.tk.documentbuilder.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import de.mimirssource.tk.documentbuilder.config.builder.ConfigurationBuilderStrategy;

public class DefaultConfigurationProvider implements Configuration {
	
	@Autowired
	private ConfigurationBuilderStrategy builderStrategy;
	
	private final Map<String,DocumentType> documentMap;
	
	public DefaultConfigurationProvider() {
		this.documentMap = this.builderStrategy.getDocumentTypeStructure();
	}

	public Map<String, DocumentType> getDocumentMap() {
		return documentMap;
	}
	
	

}
