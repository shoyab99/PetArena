package com.shoyab.petstore.Cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
        .allowedOrigins(
        	    "http://localhost:5173",
        	    "https://pet-arena-09.vercel.app",
        	    "https://pet-arena-frontend-2-git-main-shoyab1.vercel.app",
        	    "https://pet-arena-frontend-2-rglypiobz-shoyab1.vercel.app"
        	)
                .allowedMethods("GET","POST","PUT","PATCH","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}