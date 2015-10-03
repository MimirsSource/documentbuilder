package de.mimirssource.tk.documentbuilder.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import de.mimirssource.tk.documentbuilder.config.builder.ConfigurationBuilderStrategy;
import de.mimirssource.tk.documentbuilder.config.builder.DefaultConfigurationBuilderStrategy;

/**
 * The spring configuration providing environmental and file based configuration of the application.
 * @author thomas.kloppe
 *
 */
@Configuration
@PropertySource("file:config/template.properties")
public class InfrastructureConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public ConfigurationBuilderStrategy configurationBuilderStrategy() {
		return new DefaultConfigurationBuilderStrategy(environment);
	}

}
