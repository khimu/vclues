package de.frontierpsychiatrist.example.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.frontierpsychiatrist.example.oauth.controller.JdbcUserDetailsService;

/**
 * @author Moritz Schulze
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JdbcUserDetailsService jdbcUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.csrf().disable();
    	//http.csrf().ignoringAntMatchers("/oauth/authorize");
        http
            .authorizeRequests()
                .antMatchers("/login", "/logout.do").permitAll()
                .antMatchers("/**").authenticated()
            .and()
            .formLogin()
                .loginProcessingUrl("/login.do")
                .usernameParameter("name")
                .loginPage("/login")
            .and()
            .logout()
                //To match GET requests we have to use a request matcher.
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"));
    }
}
