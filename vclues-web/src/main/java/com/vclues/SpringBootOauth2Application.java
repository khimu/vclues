package com.vclues;

import java.util.Map;
import java.util.concurrent.Executor;

import javax.servlet.RequestDispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;

/*
 * it.ozimov.springboot for email template
 */
@Configuration
@ComponentScan({"com.vclues","com.vclues.core", "de.frontierpsychiatrist.example.oauth.domain", "it.ozimov.springboot"})
@EnableAsync
@SpringBootApplication(exclude = { MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, EmbeddedMongoAutoConfiguration.class })
@ImportResource({ "classpath*:spring/database.xml","classpath*:spring/mail-context.xml", "classpath*:/cache-config.xml"})
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
//@EnableTransactionManagement - caused confusion between transactionManager and oauthTransactionManager
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableMongoRepositories(basePackages = "com.vclues.core.mongo.repository")
//@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootOauth2Application extends AsyncConfigurerSupport {
	private final static Logger logger = LoggerFactory.getLogger(SpringBootOauth2Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2Application.class, args);
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
    
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                Object errorMessage = requestAttributes.getAttribute(RequestDispatcher.ERROR_MESSAGE, RequestAttributes.SCOPE_REQUEST);
                if (errorMessage != null) {
                	logger.error("Error Message " + errorMessage);
                    errorAttributes.put("message",  "There seems to be a problem with your request.  Please wait a minute and then try again.");
                }
                
                return errorAttributes;
            }

        };
        
    }

    /*
	@Autowired
	RedisConnectionFactory connectionFactory;

	private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("localhost");
		connectionFactory.setPassword("JWZokBgsRrWxcNtluxDTCgjnskxSqMcvtOvH8hfigz7k.hRbcJ1V6nu9/Xt65OGJ$2a$10$ab1zdcB.1zdcB.CgjnskxSqMcvJWZokBgsRrWxcNtluxDT$2a$10$ab1zdcB.CgjnskxSqMcvtOvH8hfigz7k.hRbcJ1V6nu9/Xt65OGJ");
		connectionFactory.setPort(7397);
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, Long> longTemplate() {

		RedisTemplate<String, Long> tmpl = new RedisTemplate<String, Long>();
		tmpl.setConnectionFactory(connectionFactory);
		tmpl.setKeySerializer(STRING_SERIALIZER);
		tmpl.setValueSerializer(LongSerializer.INSTANCE);

		return tmpl;
	}
	
	

	  @Bean
	  public CacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    cacheManager.setCacheNames(Arrays.asList(
		          "byName", "byId", "getAllCastByStoryId", "findHintBySceneId", "getNextSceneByStoryIdAndPosition", 
		          "getAllSceneByStoryId", "findScriptBySceneIdAndCastId", "storiesFindAll", "storiesFindAll", "storiesFindAll"));
	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(300);
	    return cacheManager;
	  }	
	  */
 
}
