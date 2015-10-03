package de.mimirssource.tk.documentbuilder.context;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.mimirssource.tk.documentbuilder.config.DocumentType;
import de.mimirssource.tk.documentbuilder.core.builder.TemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.builder.velocity.VelocityTemplateEvaluator;
import de.mimirssource.tk.documentbuilder.core.processor.DefaultDocumentProcessor;
import de.mimirssource.tk.documentbuilder.core.processor.DocumentProcessor;
import de.mimirssource.tk.documentbuilder.core.processor.streams.DefaultFileStreamProvider;
import de.mimirssource.tk.documentbuilder.core.processor.streams.StreamProvider;
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
	public TemplateEvaluator templateEvaluator() {
		return new VelocityTemplateEvaluator();
	}
	
	@Bean
	public DataProvider dataProvider() {
		return new FileBasedJsonDataProvider();
	}
	
	@Bean
	public StreamProvider streamProvider() {
		return new DefaultFileStreamProvider();
	}
	
	@Bean
	@Scope("prototype")
	public DocumentProcessor documentProcessor(final DocumentType documentType, final Set<String> channelList) {
		return new DefaultDocumentProcessor(documentType, channelList);
	}
}
