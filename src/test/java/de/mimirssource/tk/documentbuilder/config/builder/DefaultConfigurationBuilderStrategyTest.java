package de.mimirssource.tk.documentbuilder.config.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
@ActiveProfiles("test")
public class DefaultConfigurationBuilderStrategyTest {
	
	@Autowired
	private ConfigurationBuilderStrategy configurationBuilderStrategy;
	
	
	@Test
	public void testDefaultConfigurationBuilderStrategy() {
		Map<String, DocumentType> documentTypeStructure = configurationBuilderStrategy.getDocumentTypeStructure();
		assertEquals(3,documentTypeStructure.size());
		
		DocumentType documentType = documentTypeStructure.get("test");
		Map<String, GenerationChannel> channelMap = documentType.getChannelMap();
		assertEquals(2,channelMap.size());
		
		// configured paths for test
		GenerationChannel generationChannel = channelMap.get("html");
		assertEquals("html",generationChannel.getFileExtension());
		assertEquals("output/test/",generationChannel.getOutputDirectoryPath());
		assertEquals("templates/html/template.vm",generationChannel.getTemplateFilePath());
		
		// default path for cv
		documentType = documentTypeStructure.get("book");
		channelMap = documentType.getChannelMap();
		generationChannel = channelMap.get("html");
		assertEquals("txt",generationChannel.getFileExtension());
		assertEquals("output\\book\\html",generationChannel.getOutputDirectoryPath());
		assertEquals("templates\\book\\html",generationChannel.getTemplateFilePath());
		
		assertEquals(2,channelMap.size());
		
		
		
		// TODO !!!!!!
	}
	
	@Test
	public void testDefaultConfigurationBuilderStrategyUnknownType() {
		Map<String, DocumentType> documentTypeStructure = configurationBuilderStrategy.getDocumentTypeStructure();
		DocumentType documentType = documentTypeStructure.get("unknown");
		assertNull(documentType);
	}
	
	@Test
	public void testDefaultConfigurationBuilderStrategyUnknownChannel() {
		Map<String, DocumentType> documentTypeStructure = configurationBuilderStrategy.getDocumentTypeStructure();
		
		DocumentType documentType = documentTypeStructure.get("cv");
		GenerationChannel generationChannel = documentType.getChannelMap().get("unknown");
		assertNull(generationChannel);
	}

}
