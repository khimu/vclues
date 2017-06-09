package com.vclues.app;

import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableTransactionManagement
/*
 * This is how you configure multiple database access
 */
@Configuration
//@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

	/**
	* Setup a simple strategy: use all the defaults and return XML by default when not sure. 
	*/
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).
	            favorParameter(false).
	            ignoreAcceptHeader(false).
	            useJaf(false).
	            defaultContentType(MediaType.APPLICATION_JSON).
	            mediaType("xml", MediaType.APPLICATION_XML).
	            mediaType("json", MediaType.APPLICATION_JSON);
	}
		  
	@Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}