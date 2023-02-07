package com.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.common.repository", repositoryImplementationPostfix = "CustomImpl")
public class CustomConfig {
    @Bean(name = "messageResourceApplication")
    public MessageSource messageResourceApplication() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:application");
        return messageSource;
    }
}
