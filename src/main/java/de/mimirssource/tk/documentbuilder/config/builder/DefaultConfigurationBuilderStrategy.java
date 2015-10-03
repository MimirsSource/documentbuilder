package de.mimirssource.tk.documentbuilder.config.builder;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.config.GenerationChannel;
import de.mimirssource.tk.documentbuilder.utils.CommonUtils;

/**
 * @see de.mimirssource.tk.documentbuilder.config.builder.ConfigurationBuilderStrategy
 * 
 * @author thomas.kloppe
 *
 */
public final class DefaultConfigurationBuilderStrategy implements
		ConfigurationBuilderStrategy {
	
	private static final String PROPERTY_SEPARATOR = ".";
	private static final String OUTPUT_PREFIX = "output.result.";
	private static final String EXTENSION_PREFIX = "output.extension.";
	
	private static final String DEFAULT_EXTENSION = "txt";
	
	private Environment environment;
	private Map<String, DocumentType> documentTypeMap;

	public DefaultConfigurationBuilderStrategy(Environment environment) {
		this.environment = environment;
		init();
	}

	@Override
	/**
	 * @{inheritDoc}
	 */
	public Map<String,DocumentType> getDocumentTypeStructure() {
		return this.documentTypeMap;
	}

	private void init() {
		Map<String, DocumentType> result = new HashMap<String, DocumentType>();
		String typeids = this.environment.getProperty("typeids");
		String channelids = this.environment.getProperty("channelids");
		
		Collection<String> typeColl = CommonUtils.collectionFromCommaSeparatedList(typeids);
		Collection<String> channelColl = CommonUtils.collectionFromCommaSeparatedList(channelids);
		
		for( String type : typeColl) {
			Map<String, GenerationChannel> generationChannelMap = new HashMap<String, GenerationChannel>();
			for( String channel : channelColl ) {
				String templateFilePath = this.environment.getProperty(type+PROPERTY_SEPARATOR+channel);
				if( templateFilePath == null ) {
					templateFilePath = buildDefaultTemplateFilePath(type, channel);
				}
				String outputFilePath  = this.environment.getProperty(OUTPUT_PREFIX+type+PROPERTY_SEPARATOR+channel);
				if( outputFilePath == null ) {
					outputFilePath = buildDefaultOutputPath(type, channel);
				}
				String fileExtension  = this.environment.getProperty(EXTENSION_PREFIX+type+PROPERTY_SEPARATOR+channel);
				if( fileExtension == null || fileExtension.isEmpty() ) {
					fileExtension = DEFAULT_EXTENSION;
				}
				GenerationChannel generationChannel = new GenerationChannel(channel, templateFilePath, outputFilePath, fileExtension);
				generationChannelMap.put(channel, generationChannel);
			}
			DocumentType documentType = new DocumentType(type, generationChannelMap);
			result.put(type, documentType);
		}

		this.documentTypeMap = result;
	}
	
	private String buildDefaultTemplateFilePath(String type, String channel) {
		return "templates"+File.separator+type+File.separator+channel;
	}
	
	private String buildDefaultOutputPath(String type, String channel) {
		return "output"+File.separator+type+File.separator+channel;
	}
	

}
