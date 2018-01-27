package com.todoproject.sample.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
//public class ToDoConfiguration {
@EnableConfigurationProperties({ResourceProperties.class})
public class ToDoConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	private ResourceProperties resourceProp = new ResourceProperties();
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHanlder) {
    	Integer cachePeriod = resourceProp.getCachePeriod();
    	
    	final String[] staticLocations = resourceProp.getStaticLocations();
    	final String[] indexLocation = new String[staticLocations.length];
    	
    	for(int i=0; i<staticLocations.length; i++){
    		indexLocation[i] = staticLocations[i]+"index.html";
    	}
    	
    	resourceHanlder.addResourceHandler(
    			"/**/*.css",
    			"/**/*.html",
    			"/**/*.js",
    			"/**/*.eot",
    			"/**/*.woff",
    			"/**/*.woff2"
    			)
    	.addResourceLocations(staticLocations)
    	.setCachePeriod(cachePeriod);
    	resourceHanlder.addResourceHandler("/api/**")
    	.addResourceLocations(indexLocation)
    	.setCachePeriod(cachePeriod)
    	.resourceChain(true);
    	
    	resourceHanlder.addResourceHandler("/todos", "/", "/profile")
    	.addResourceLocations(indexLocation)
    	.setCachePeriod(cachePeriod)
    	.resourceChain(true)
    	.addResolver(new PathResourceResolver(){
    		@Override
    		protected Resource getResource(String resourcePath, Resource location) throws IOException {
    			return location.exists() && location.isReadable() ? location : null;
    		}
    	});
    }
}