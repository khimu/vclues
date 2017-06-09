package de.frontierpsychiatrist.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Moritz Schulze
 */
@SpringBootApplication(exclude = { MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@ImportResource({ "classpath:spring/oauth-database.xml" })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "de.frontierpsychiatrist.example.oauth.repository")
@ComponentScan({"de.frontierpsychiatrist.example"})
public class OauthServerMain {

    /**
     * Main data source containing the credentials.
     * In this is example this is the DB from the resource server.
     * Applied to JapRepository via DatabaseConfiguration
     */
	/*
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource_oauth")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }
	*/

    public static void main(String[] args) {
        SpringApplication.run(OauthServerMain.class, args);
    }

}
