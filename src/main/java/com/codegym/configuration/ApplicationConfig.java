package com.codegym.configuration;


import com.codegym.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

//    private ApplicationContext applicationContext;
//
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new CategoryFormatter(applicationContext.getBean(CategoryService.class)));
//
//    }

    private final long MAX_AGE_SECS = 3600;

    @Value("${app.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(false)
                .maxAge(MAX_AGE_SECS);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
