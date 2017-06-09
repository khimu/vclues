package com.vclues.core.repository;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.entity.Authority;
import com.vclues.core.repository.AuthorityRepository;

@Ignore
@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
public class AuthorityRepositoryTest {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private AuthorityRepository authorityRepository;
	
	private Authority authority1;
	private Authority authority2;
	private Authority authority3;

	@Before
	public void setUp() {
		authority1 = new Authority("ROLE_ANONYMOUS");
		authority2 = new Authority("ROLE_USER");
		authority3 = new Authority("ROLE_ADMIN");
		authorityRepository.save(Arrays.asList(authority1, authority2, authority3));
	}

	@After
	public void destroy() {
		authorityRepository.delete(Arrays.asList(authority1, authority2, authority3));
	}
	
	@Test
    public void testFindByName() {

		Authority authority = authorityRepository.findByName("ROLE_USER");
        Assert.assertEquals("ROLE_USER", authority.getName());
    }
	
	@Test
    public void testFindAll() {

		List<Authority> all = authorityRepository.findAll();
        Assert.assertEquals(3, all.size());
    }
	
}
