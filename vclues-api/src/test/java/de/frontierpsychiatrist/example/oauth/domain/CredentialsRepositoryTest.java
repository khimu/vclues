package de.frontierpsychiatrist.example.oauth.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.frontierpsychiatrist.example.oauth.repository.CredentialsRepository;

@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
public class CredentialsRepositoryTest {

	@Resource
	private CredentialsRepository credentialsRepository;
	
	@Test
	public void testCredentialsRepositoryTable() {
		Credentials credential = credentialsRepository.findByName("oauth_admin");
		if(credential != null) {
			System.out.println("credential is not null "  + credential.getName());
		}
	}
}
