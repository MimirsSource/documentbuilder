package de.mimirssource.tk.documentbuilder.config;

import java.util.HashMap;
import java.util.Map;

public class DocumentType {
	
	private String uniqueName;
	private Map<String,GenerationChannel> channelMap = new HashMap<String, GenerationChannel>();
	
	public DocumentType(final String uniqueName, final Map<String,GenerationChannel> channelMap ) {
		this.uniqueName = uniqueName;
		this.channelMap = channelMap;
	}
	
	public String getUniqueName() {
		return uniqueName;
	}
	
	public Map<String, GenerationChannel> getChannelMap() {
		return channelMap;
	}
	
	// TODO - apply rules for data structure	

}
