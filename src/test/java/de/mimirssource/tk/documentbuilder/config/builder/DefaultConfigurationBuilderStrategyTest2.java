package de.mimirssource.tk.documentbuilder.config.builder;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.config.GenerationChannel;
import de.mimirssource.tk.documentbuilder.context.ApplicationConfig;
import de.mimirssource.tk.documentbuilder.context.InfrastructureConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class,InfrastructureConfig.class}, loader=AnnotationConfigContextLoader.class)
@ActiveProfiles("test2")
public class DefaultConfigurationBuilderStrategyTest2 {
	
	@Autowired
	private ConfigurationBuilderStrategy configurationBuilderStrategy;
	
	@Test
	public void testDefaultConfigurationBuilderStrategy() {
		Map<String, DocumentType> documentTypeStructure = configurationBuilderStrategy.getDocumentTypeStructure();
		assertEquals(3,documentTypeStructure.size());
		
		DocumentType documentType = documentTypeStructure.get("test");
		Map<String, GenerationChannel> channelMap = documentType.getChannelMap();
		assertEquals(0,channelMap.size());
		
		documentType = documentTypeStructure.get("book");
		channelMap = documentType.getChannelMap();
		assertEquals(0,channelMap.size());
		
		documentType = documentTypeStructure.get("cv");
		channelMap = documentType.getChannelMap();
		assertEquals(0,channelMap.size());

	}

}
