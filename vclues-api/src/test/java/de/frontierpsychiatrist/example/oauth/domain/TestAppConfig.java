package de.frontierpsychiatrist.example.oauth.domain;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@SpringBootApplication(exclude = { MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@TestPropertySource(locations = "classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "oauthEntityManagerFactory",
transactionManagerRef = "oauthTransactionManager", basePackages = "de.frontierpsychiatrist.example.oauth.repository")
@ImportResource({ "classpath*:spring/oauth-database.xml"})
@EnableTransactionManagement
public class TestAppConfig {
	
	@Resource
	private DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(TestAppConfig.class, args);
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public PasswordEncoder getBCryptPasswordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
