package com.jtb.taxpayerws.config;

import freemarker.template.TemplateExceptionHandler;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.StandardCharsets;

@Configuration
public class ProjectConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }

    @Bean
    public freemarker.template.Configuration freemarkerConfig() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_31);

        configuration.setClassForTemplateLoading(ProjectConfig.class, "/templates");

        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        configuration.setLogTemplateExceptions(false);

        configuration.setWrapUncheckedExceptions(true);

        configuration.setFallbackOnNullLoopVariable(false);

        return configuration;
    }
}
