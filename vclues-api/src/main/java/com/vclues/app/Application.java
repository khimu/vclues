package com.vclues.app;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({"com.vclues.api","de.frontierpsychiatrist.example.oauth.domain", "com.vclues.core", "it.ozimov.springboot"})
@EnableAsync
@SpringBootApplication(exclude = { MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@ImportResource({ "classpath*:spring/database.xml" })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
//@EnableTransactionManagement
@Import(Config.class)
@EnableCaching
@EnableMongoRepositories(basePackages = "com.vclues.core.mongo.repository")
public class Application extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = 
                      new TomcatEmbeddedServletContainerFactory();
        return factory;
     }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("vclues-");
        executor.initialize();
        return executor;
    }
    
	/* disable redis for now
	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("localhost");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	@Bean
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}
*/

}
