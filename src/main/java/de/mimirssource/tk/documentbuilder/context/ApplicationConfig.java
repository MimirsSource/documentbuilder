package de.mimirssource.tk.documentbuilder.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ActiveProfiles;

import de.mimirssource.tk.documentbuilder.core.builder.TemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.builder.VelocityTemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.io.FileBasedOutputProvider;
import de.mimirssource.tk.documentbuilder.core.io.FileBasedTemplateProvider;
import de.mimirssource.tk.documentbuilder.core.io.OutputProvider;
import de.mimirssource.tk.documentbuilder.core.io.TemplateProvider;
import de.mimirssource.tk.documentbuilder.core.processor.DefaultDocumentProcessor;
import de.mimirssource.tk.documentbuilder.core.processor.DocumentProcessor;
import de.mimirssource.tk.documentbuilder.dataprovider.DataProvider;
import de.mimirssource.tk.documentbuilder.dataprovider.json.FileBasedJsonDataProvider;

/**
 * Initializes beans and component scan for a file based document builder working on the local machine.
 * 
 * @author thomas.kloppe
 *
 */
@Configuration
@ComponentScan("de.mimirssource.tk.documentbuilder.context, de.mimirssource.tk.documentbuilder.config, de.mimirssource.tk.documentbuilder.core.processor")
public class ApplicationConfig {
	
	@Bean
	@Scope("prototype")
	public TemplateEvaluator templateEvaluator() {
		return new VelocityTemplateEvaluator();
	}
	
	@Bean
	@Scope("prototype")
	public DataProvider dataProvider() {
		return new FileBasedJsonDataProvider();
	}
	
	@Bean
	@Scope("prototype")
	public TemplateProvider templateProvider() {
		return new FileBasedTemplateProvider();
	}
	
	@Bean
	@Scope("prototype")
	public OutputProvider outputProvider() {
		return new FileBasedOutputProvider();
	}
	
	@Bean
	@Scope("prototype")
	public DocumentProcessor documentProcessor() {
		return new DefaultDocumentProcessor();
	}
}
