package com.tpintegrador.libros.corsConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173","http://172.25.0.4:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
        System.out.println("Cors configurado correctamente");
    }
}
