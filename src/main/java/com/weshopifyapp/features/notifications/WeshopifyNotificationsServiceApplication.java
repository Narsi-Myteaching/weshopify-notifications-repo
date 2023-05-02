package com.weshopifyapp.features.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@SpringBootApplication
public class WeshopifyNotificationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyNotificationsServiceApplication.class, args);
	}
	
	@Primary
	@Bean
	FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean factory = new FreeMarkerConfigurationFactoryBean();
		factory.setTemplateLoaderPath("classpath:/templates");
		return factory;
	}

}
