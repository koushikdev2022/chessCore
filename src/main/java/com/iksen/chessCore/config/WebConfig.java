package com.iksen.chessCore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/uploads/**")
            .addResourceLocations("file:public/uploads/")
            .setCachePeriod(3600)                        
            .resourceChain(true)                        
            .addResolver(new VersionResourceResolver()
                .addContentVersionStrategy("/**"));     
    }
}
