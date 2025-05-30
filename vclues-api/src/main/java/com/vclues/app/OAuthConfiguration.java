package com.vclues.app;

import java.util.Collections;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Moritz Schulze
 */
@Configuration
@EnableResourceServer
@EnableJpaRepositories(entityManagerFactoryRef = "oauthEntityManagerFactory",
transactionManagerRef = "oauthTransactionManager", basePackages = "de.frontierpsychiatrist.example.oauth.repository")
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {
	
	@Resource(name = "oauthDataSource")
	private DataSource oauthDataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        TokenStore tokenStore = new JdbcTokenStore(oauthDataSource);
        resources.resourceId("partner-services")
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }
    
    /**
     * This special filter is needed so unauthorized request that are rejected by Spring security
     * still have CORS headers.
     * For some reason he {@code bean.setOrder} call is not enough, the configuration also needs
     * {@code security.filter-order=5} for the CORS filter to be in front of the Spring Security
     * filter.
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        //based on https://github.com/spring-projects/spring-boot/issues/5834
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(5);
        return bean;
    }
    
}
