package de.mimirssource.tk.documentbuilder.core.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.config.GenerationChannel;
import de.mimirssource.tk.documentbuilder.core.builder.TemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.processor.streams.StreamProvider;

/**
 * @see de.mimirssource.tk.documentbuilder.core.processor.DocumentProcessor
 */
public final class DefaultDocumentProcessor implements DocumentProcessor {

	private DocumentType documentType;
	private Set<String> channelList;

	@Autowired
	TemplateEvaluator templateEvaluator;

	@Autowired
	StreamProvider streamProvider;

	public DefaultDocumentProcessor(final DocumentType documentType) {
		this.documentType = documentType;
	}

	public DefaultDocumentProcessor(final DocumentType documentType, final Set<String> channelList) {
		this.documentType = documentType;
		this.channelList = channelList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void processDocuments(final Map<String,Object> contentObjectMap) {
		Map<String, GenerationChannel> channelMap = getFilteredChannelMap();

		for(Entry<String, GenerationChannel> channelEntry : channelMap.entrySet() ) {
			GenerationChannel generationChannel = channelEntry.getValue();		
			

			for(Entry<String,Object> objectEntry : contentObjectMap.entrySet()) {
				InputStream inputStream = streamProvider.getTemplateInputStream(generationChannel);
				OutputStream outputStream = streamProvider.getOutputStream(generationChannel, objectEntry.getKey(), objectEntry.getValue());
				templateEvaluator.evaluateTemplate(inputStream, outputStream, objectEntry.getValue());
				
				try {
					outputStream.flush();
					outputStream.close();
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private Map<String, GenerationChannel> getFilteredChannelMap() {
		Map<String, GenerationChannel> channelMap = documentType.getChannelMap();
		Map<String, GenerationChannel> filteredMap = new HashMap<String, GenerationChannel>();
		if(this.channelList != null && !this.channelList.isEmpty()) {
			for(String channelName : this.channelList ) {
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
