package de.frontierpsychiatrist.example.oauth.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.repository.TestAppConfig;

import de.frontierpsychiatrist.example.oauth.repository.CredentialsRepository;


@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "oauthEntityManagerFactory",
transactionManagerRef = "oauthTransactionManager", basePackages = "de.frontierpsychiatrist.example.oauth.repository")
public class CredentialsRepositoryTest {

	@Resource
	private CredentialsRepository credentialsRepository;
	
	@Test
	public void testCredentialsRepositoryTable() {
		Credentials credential = credentialsRepository.findByName("khim");
		if(credential == null) {
			System.out.println("credential is not null");
		}
	}
}
