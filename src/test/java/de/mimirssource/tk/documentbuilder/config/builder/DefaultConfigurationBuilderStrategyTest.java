package de.mimirssource.tk.documentbuilder.config.builder;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.context.ApplicationConfig;
import de.mimirssource.tk.documentbuilder.context.InfrastructureConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class,InfrastructureConfig.class}, loader=AnnotationConfigContextLoader.class)
public class DefaultConfigurationBuilderStrategyTest {
	
	@Autowired
	private Environment environment;
	
	@Test
	public void testDefaultConfigurationBuilderStrategy() {
		DefaultConfigurationBuilderStrategy defaultConfigurationBuilderStrategy = new DefaultConfigurationBuilderStrategy(this.environment);
		Map<String, DocumentType> documentTypeStructure = defaultConfigurationBuilderStrategy.getDocumentTypeStructure();
		DocumentType documentType = documentTypeStructure.get("cv");
		documentType.getUniqueName();
		// TODO !!!!!!
	}

}
