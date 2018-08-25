package com.acme.fuel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class SpringMVCConfig {
	@Bean
	public MultipartResolver multipartResolver() {
	    return new CommonsMultipartResolver();
	}
}
