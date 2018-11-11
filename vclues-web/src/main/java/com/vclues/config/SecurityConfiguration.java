package com.vclues.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /*
     * configure(HttpSecurity) allows configuration of web based security at a resource level, based on a selection match - e.g. The example below restricts the URLs that start with /admin/ to users that have ADMIN role, and declares that any other URLs need to be successfully authenticated.
     * 
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*
    	 * Must define here to use sec:authorize="isAuthenticated()"
    	 */
    	//http.csrf().ignoringAntMatchers("/nocsrf","/users/**");
    	
    	/*
    	 * .antMatchers("/resources/**", "/signup", "/about").permitAll()                  2
			.antMatchers("/admin/**").hasRole("ADMIN")                                      3
			.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            4
			.anyRequest().authenticated()         
    	 */
    	
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeRequests().antMatchers("/assets/**").permitAll();
        
        
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("WRITER");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('WRITER')");
        http.authorizeRequests().antMatchers("/signup").anonymous();
        http.authorizeRequests().antMatchers("/fblogin").anonymous();
        
        //http.authorizeRequests().antMatchers("/registerStep2").anonymous();
        //http.authorizeRequests().antMatchers("/registerStep3").anonymous();
        //http.authorizeRequests().antMatchers("/index.html").anonymous();

        http.authorizeRequests().antMatchers("/contact.html").anonymous();
        http.authorizeRequests().antMatchers("/privacy.html").anonymous();
        http.authorizeRequests().antMatchers("/terms.html").anonymous();
        //http.authorizeRequests().antMatchers("/skip").anonymous();
        http.authorizeRequests().antMatchers("/confirm/**").anonymous();
        http.authorizeRequests().antMatchers("/confirm").anonymous();
        http.authorizeRequests().antMatchers("/error").anonymous();
        http.authorizeRequests().antMatchers("/error/**").anonymous();
        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/auth/**" ).permitAll().antMatchers("/**").hasRole("USER");

         http.formLogin()
         .loginPage("/login")
         .failureUrl("/login?error")
         .usernameParameter("email")
         .permitAll()
         .and()
         .apply(new SpringSocialConfigurer()
         .postLoginUrl("/")
         .alwaysUsePostLoginUrl(true))
         .and()
         .logout()
         .logoutUrl("/logout")
         .logoutSuccessUrl("/login")
         .deleteCookies("remember-me")
         .logoutSuccessUrl("/")
         .permitAll()
         .and()
         .rememberMe();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }
    
    /**
     * This bean is used to load the user specific data when social sign in
     * is used.
     */
    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailService(userDetailsService());
    }    

    /*
     * configure(WebSecurity) is used for configuration settings that impact global security (ingore resources, set debug mode, reject requests by implementing a custom firewall definition). 
     * For example, the following method would cause any request that starts with /resources/ to be ignored for authentication purposes.
     * 
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    	/*
    	 * ignoring resources under webapp/resources but can't remember now
    	 */
        web.ignoring()
                .antMatchers("/h2console/**")
                .antMatchers("/resources/**")
                .antMatchers("/assets/**");
    }

    /*
     * configure(AuthenticationManagerBuilder) is used to establish an authentication mechanism by allowing AuthenticationProviders 
     * to be added easily: e.g. The following defines the in-memory authentication with the in-built 'user' and 'admin' logins.
     * 
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManagerBean()
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    	
    	public GlobalSecurityConfiguration() {
    	}
    	
        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }

    }

}

