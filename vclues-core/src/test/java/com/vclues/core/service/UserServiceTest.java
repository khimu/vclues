package com.vclues.core.service;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.entity.User;
import com.vclues.core.enums.Features;
import com.vclues.core.repository.AuthorityRepository;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.repository.TestAppConfig;
import com.vclues.core.repository.UserRepository;
import com.vclues.core.utils.BitOperations;

/*
 * Arcturian Race
 * 
 */
@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
public class UserServiceTest {
	
    @Autowired
    private IUserService userService;

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private AuthorityRepository authorityRepository;
	
	@Resource
	private UserRepository userRepository;
	
	@Resource 
	private StoryRepository businessRepository;
	
	private User user;

	@Before
	public void setUp() {		
	}

	@After
	public void destroy() {
		userRepository.delete(user);
	}
	
	@Test
	public void testSave() {
		Assert.assertEquals(0, userRepository.findAll().size());
		
		Assert.assertEquals(0, businessRepository.findAll().size());
		
		user = new User("kim2kim@gmail.com", "password", true);
		user.setAccountType("Partner");
		
		user = userService.registerNewUser(user);
		
		Assert.assertEquals(1, userRepository.findAll().size());
		
		Assert.assertEquals(1, businessRepository.findAll().size());

		for(Features feature : Features.values()) {
			Assert.assertTrue(BitOperations.isTrue(user.getPermissions(), feature.getBit()));
		}
	}
	
	@Test
	public void testSaveBusinessExist() {
		Assert.assertEquals(0, userRepository.findAll().size());
		
		Assert.assertEquals(1, businessRepository.findAll().size());
		
		user = new User("kim2kim@gmail.com", "password", true);
		user.setAccountType("Partner");

		userService.registerNewUser(user);
		
		Assert.assertEquals(1, userRepository.findAll().size());
		
		Assert.assertEquals(1, businessRepository.findAll().size());
	}

}
