package de.mimirssource.tk.documentbuilder.core.processor;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.config.GenerationChannel;
import de.mimirssource.tk.documentbuilder.config.builder.ConfigurationBuilderStrategy;
import de.mimirssource.tk.documentbuilder.core.builder.TemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.io.OutputProvider;
import de.mimirssource.tk.documentbuilder.core.io.TemplateProvider;
import de.mimirssource.tk.documentbuilder.exceptions.DocumentProcessorException;
import de.mimirssource.tk.documentbuilder.exceptions.TemplateEvaluationException;

/**
 * @see de.mimirssource.tk.documentbuilder.core.processor.DocumentProcessor
 */
public final class DefaultDocumentProcessor implements DocumentProcessor {

	@Autowired
	private ConfigurationBuilderStrategy configurationBuilder;

	@Autowired
	private TemplateEvaluator templateEvaluator;
	
	@Autowired
	private TemplateProvider templateProvider;

	@Autowired
	private OutputProvider outputProvider;

	/**
	 * {@inheritDoc}
	 */
	public void processDocuments(final Map<String,Object> contentObjectMap, final String documentTypeName ) throws DocumentProcessorException {
		processDocuments(contentObjectMap, documentTypeName, new HashSet<String>()) ;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void processDocuments(final Map<String,Object> contentObjectMap, final String documentTypeName, final Set<String> channelList) throws DocumentProcessorException {
		DocumentType documentType = retrieveDocumentType(documentTypeName);
		if(documentType != null) {
			Map<String, GenerationChannel> channelMap = getFilteredChannelMap(documentType, channelList);

			for(Entry<String, GenerationChannel> channelEntry : channelMap.entrySet() ) {
				GenerationChannel generationChannel = channelEntry.getValue();		
				processForChannel(contentObjectMap, generationChannel);
			}
		}
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void processDocuments(Map<String, Object> contentObjectMap,
			String documentTypeName, String channelName)
			throws DocumentProcessorException {
		DocumentType documentType = retrieveDocumentType(documentTypeName);
		GenerationChannel generationChannel = documentType.getChannelMap().get(channelName);
		if( generationChannel != null ) {
			processForChannel(contentObjectMap, generationChannel);
		} else {
			throw new DocumentProcessorException("No channel for name "+channelName+" found!");
		}
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setTemplateProvider(final TemplateProvider templateProvider) {
		this.templateProvider = templateProvider;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setOutputProvider(final OutputProvider outputProvider) {
		this.outputProvider = outputProvider;
	}
	
	private DocumentType retrieveDocumentType(final String documentTypeName) {
		Map<String, DocumentType> typeStructure = configurationBuilder.getDocumentTypeStructure();
		DocumentType documentType = typeStructure.get(documentTypeName);
		return documentType;
	}

	private void processForChannel(Map<String, Object> contentObjectMap,
			GenerationChannel generationChannel)
			throws TemplateEvaluationException {
		for(Entry<String,Object> objectEntry : contentObjectMap.entrySet()) {
			Reader templateReader = this.templateProvider.getTemplateReader(generationChannel);
			Writer writer = this.outputProvider.getWriter(generationChannel, objectEntry.getKey(), objectEntry.getValue());
			this.templateEvaluator.evaluateTemplate(templateReader, writer, objectEntry.getValue());
			this.outputProvider.handleFinaleActions(writer);
		}
	}

	private Map<String, GenerationChannel> getFilteredChannelMap(final DocumentType documentType, final Set<String> channelList) {
		Map<String, GenerationChannel> channelMap = documentType.getChannelMap();
		Map<String, GenerationChannel> filteredMap = new HashMap<String, GenerationChannel>();
		if(channelList != null && !channelList.isEmpty()) {
			for(String channelName : channelList ) {
				GenerationChannel generationChannel = channelMap.get(channelName);
				if(generationChannel != null) {
					filteredMap.put(channelName, generationChannel);
				}
			}
		} 
		else {
			filteredMap = channelMap;
		}
		return filteredMap;
	}





}
