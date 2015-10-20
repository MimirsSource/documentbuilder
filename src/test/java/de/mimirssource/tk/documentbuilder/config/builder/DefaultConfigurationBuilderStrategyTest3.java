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
import de.mimirssource.tk.documentbuilder.context.ApplicationConfig;
import de.mimirssource.tk.documentbuilder.context.InfrastructureConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class,InfrastructureConfig.class}, loader=AnnotationConfigContextLoader.class)
@ActiveProfiles("test3")
public class DefaultConfigurationBuilderStrategyTest3 {
	
	@Autowired
	private ConfigurationBuilderStrategy configurationBuilderStrategy;
	
	@Test
	public void testDefaultConfigurationBuilderStrategy() {
		Map<String, DocumentType> documentTypeStructure = configurationBuilderStrategy.getDocumentTypeStructure();
		assertEquals(0,documentTypeStructure.size());
	}
	
}
