package com.vclues.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
public class SocialConfig {// implements SocialConfigurer {
	
	
	//@Autowired
    private DataSource dataSource;

	    //@Override
	    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
	        connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
	            environment.getProperty("spring.social.facebook.appId"),
	            environment.getProperty("spring.social.facebook.appSecret")));

	        /*
	        connectionFactoryConfigurer.addConnectionFactory(new LiveConnectionFactory(
	            environment.getProperty("spring.social.live.appId"),
	            environment.getProperty("spring.social.live.appSecret")));
	        */
	        /*
	         * cfConfig.addConnectionFactory(new TwitterConnectionFactory(
                env.getProperty("twitter.consumer.key"),
                env.getProperty("twitter.consumer.secret")
        ));
	         */
	    }

		//@Override
		public UserIdSource getUserIdSource() {
			return new AuthenticationNameUserIdSource();
		}

		//@Override
		public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	        return new JdbcUsersConnectionRepository(
	                dataSource,
	                connectionFactoryLocator,
	                /**
	                 * The TextEncryptor object encrypts the authorization details of the connection. In
	                 * our example, the authorization details are stored as plain text.
	                 * DO NOT USE THIS IN PRODUCTION.
	                 */
	                Encryptors.noOpText()
	        );
		}
		
		/**
	     * This bean manages the connection flow between the account provider and
	     * the example application.
	     */
	    @Bean
	    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
	        return new ConnectController(connectionFactoryLocator, connectionRepository);
	    }
}
