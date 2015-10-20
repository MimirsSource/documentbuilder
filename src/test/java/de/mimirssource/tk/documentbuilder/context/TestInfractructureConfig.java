package de.mimirssource.tk.documentbuilder.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import de.mimirssource.tk.documentbuilder.config.builder.ConfigurationBuilderStrategy;
import de.mimirssource.tk.documentbuilder.config.builder.DefaultConfigurationBuilderStrategy;

@Configuration
@PropertySource("classpath:/config/template.properties")
@Profile("test")
public class TestInfractructureConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public ConfigurationBuilderStrategy configurationBuilderStrategy() {
		return new DefaultConfigurationBuilderStrategy(environment);
	}

}
