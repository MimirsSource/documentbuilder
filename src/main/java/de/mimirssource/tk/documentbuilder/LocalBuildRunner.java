package de.mimirssource.tk.documentbuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.mimirssource.tk.documentbuilder.context.ApplicationConfig;
import de.mimirssource.tk.documentbuilder.context.InfrastructureConfig;
import de.mimirssource.tk.documentbuilder.core.processor.DocumentProcessor;
import de.mimirssource.tk.documentbuilder.dataprovider.DataProvider;
import de.mimirssource.tk.documentbuilder.exceptions.DocumentProcessorException;

public class LocalBuildRunner {
	
	private String type = null;
	private Set<String> channelSet = new HashSet<String>();
	private String sourceUri = null;
	

	public LocalBuildRunner(String[] args) {
		/*
		 * Arguments:
		 * -C<channel> 0-*
		 * -T<type> 1
		 * -S<source-uri> 1
		 */
		for(String arg : args ) {
			if(arg.startsWith("-T") && arg.length() > 2) {
				type = arg.substring(2);
			} else if(arg.startsWith("-S") && arg.length() > 2) {
				sourceUri = arg.substring(2);
			} else if(arg.startsWith("-C") && arg.length() > 2) {
				channelSet.add(arg.substring(2));
			}
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Type parameter -T must not be null!");
		}
		if(sourceUri == null) {
			throw new IllegalArgumentException("Source-URI parameter -S must not be null!");
		}
	}
	
	public LocalBuildRunner(final String type, final String sourceUri, final Set<String> channelSet ) {
		this.type = type;
		this.sourceUri = sourceUri;
		this.channelSet = channelSet;
	}



	public void runApplication() {
		System.setProperty("spring.profiles.active", "production");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class, InfrastructureConfig.class);
		DataProvider dataProvider = context.getBean("dataProvider",DataProvider.class);
		
		try {
			DocumentProcessor documentProcessor = context.getBean(DocumentProcessor.class);
			Map<String, Object> contentObjectMap = dataProvider.obtainData(new URI(this.sourceUri));
			documentProcessor.processDocuments(contentObjectMap,this.type,this.channelSet);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Could not open URI at "+this.sourceUri,e);
		} catch (DocumentProcessorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			((ConfigurableApplicationContext)context).close();
		}

	}
	
	public static void main(String[] args) {
		LocalBuildRunner consoleRunner = new LocalBuildRunner(args);
		consoleRunner.runApplication();
	}

}
