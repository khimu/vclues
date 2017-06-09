package com.vclues.core.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.entity.Authority;
import com.vclues.core.entity.User;
import com.vclues.core.repository.AuthorityRepository;
import com.vclues.core.repository.UserRepository;
import com.vclues.core.security.Authorities;

@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
public class UserRepositoryTest {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private AuthorityRepository authorityRepository;
	
	@Resource
	private UserRepository userRepository;
	
	private User user1;
	private User user2;
	private User user3;

	private String emailConfirmation;
	
	private String resetPasswordConfirmation;

	@Before
	public void setUp() {		
		user1 = new User("kim@art.com", "password", true);
		user2 = new User("jon@art.com", "password", false);
		user3 = new User("sam@art.com", "password", false);

		user1.setFirstName("firstname");
		user1.setLastName("lastname");
		user1.setPhone("3108540201");
		user1.setParentUser(user1);
		
		user1.setLastName("lastname-0");

		user2.setLastName("lastname-1");
		user2.setParentUser(user2);

		user3.setLastName("lastname-2");
		user3.setParentUser(user3);
		
		// only confirm email for one user
		emailConfirmation = RandomStringUtils.randomAlphabetic(4);
		
		// When a user confirms email, look user up by email and activation key
		user2.setActivationKey(emailConfirmation);
		
		resetPasswordConfirmation = RandomStringUtils.randomAlphabetic(4);
		user3.setResetPasswordKey(resetPasswordConfirmation);
		
		user1.getAuthorities().add(authorityRepository.findByName("ROLE_USER"));
		user2.getAuthorities().add(authorityRepository.findByName("ROLE_ADMIN"));
		user3.getAuthorities().add(authorityRepository.findByName("ROLE_ANONYMOUS"));
		
		userRepository.save(Arrays.asList(user1, user2, user3));
	}

	@After
	public void destroy() {
		userRepository.delete(Arrays.asList(user1, user2, user3));
	}
	
	@Test
	public void testFindAll() {
		List<User> users = userRepository.findAll();
		
		Assert.assertEquals(3, users.size());
	}
	
	@Test
	public void testFindByActivated() {
		List<User> users = userRepository.findByActivated(true);
		
		Assert.assertEquals(1, users.size());
	}
	
	
	@Test
	public void testFindByEmailAndActivationKey() {
		User user = userRepository.findByEmailAndActivationKey("joN@art.com", emailConfirmation);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getAuthorities().size());
		Assert.assertEquals("jon@art.com", user.getEmail());
	}
	
	@Test
	public void testFindByWrongEmailAndActivationKey() {
		User user = userRepository.findByEmailAndActivationKey("sam@art.com", emailConfirmation);
		
		Assert.assertNull(user);
	}
	
	@Test
	public void testFindByEmail() {
		User user = userRepository.findByEmail("sAm@art.com");
		
		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getAuthorities().size());
		Assert.assertEquals("sam@art.com", user.getEmail());
	}
	
	/*
	 * When a user resets their password, generate a reset password, save it to the user's account
	 * and when the user clicks on the email link, reactivate the user
	 */
	@Test
	public void testFindByEmailAndResetPasswordKey() {
		User user = userRepository.findByEmailAndResetPasswordKey("Sam@art.com", resetPasswordConfirmation);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getAuthorities().size());
		Assert.assertEquals("ROLE_ANONYMOUS", user.getAuthorities().iterator().next().getName());
		Assert.assertEquals("sam@art.com", user.getEmail());
	}
	
	@Test
	public void testFindByParentUser() {
		Iterable<User> users = userRepository.findByParentUser(user1);
		
		Assert.assertNotNull(users);
		Assert.assertTrue(users.iterator().hasNext());
		Iterator<User> itr = users.iterator();
		User user = itr.next();
		Assert.assertEquals("kim@art.com", user.getEmail());
		Assert.assertEquals(Authorities.ROLE_USER.name(), user.getAuthorities().iterator().next().getName());
	
		Assert.assertFalse(itr.hasNext());
	}
	
	@Test
	public void testFindByParentUserNull() {
		Iterable<User> users = userRepository.findByParentUser(null);
		
		Assert.assertNotNull(users);
		Assert.assertFalse(users.iterator().hasNext());
	}
	
	
	@Test
	public void testFindByEmailAndResetPasswordKeyNotFound() {
		User user = userRepository.findByEmailAndResetPasswordKey("jon@art.com", resetPasswordConfirmation);
		
		Assert.assertNull(user);
	}
	
	@Test
	public void testDelete() {
		userRepository.delete(user1);
		
		List<Authority> all = authorityRepository.findAll();
		
		Assert.assertNotNull(all);
		Assert.assertEquals(3, all.size());
	}
	
	@Test
	public void findSavedUserByLastname() throws Exception {
		List<User> accounts = userRepository.findByLastName("lastname-0");

		assertThat(accounts, is(notNullValue()));
		assertThat(accounts.contains(user1), is(true));
	}

	@Test
	public void findByFirstnameOrLastname() throws Exception {

		List<User> accounts = userRepository
				.findByFirstNameOrLastName("lastname-0");

		assertThat(accounts.contains(user1), is(true));
	}

	@Test
	public void findFirst2ByOrderByLastnameAsc() {
		List<User> result = userRepository.findFirst2ByOrderByLastNameAsc();

		Assert.assertEquals(2, result.size());
		//assertThat(result.size(), is(2));
		//assertThat(result, hasItems(account, account2));
		
		//accountRepository.delete(Arrays.asList(account, account2, account3));
	}

	@Test
	public void findTop2ByWithSort() {

		List<User> resultAsc = userRepository.findTop2By(new Sort(Direction.ASC,
				"lastName"));

		Assert.assertEquals(2, resultAsc.size());
		//assertThat(resultAsc.size(), is(2));
		//assertThat(resultAsc, hasItems(account, account2));

		List<User> resultDesc = userRepository.findTop2By(new Sort(
				Direction.DESC, "lastName"));

		Assert.assertEquals(2, resultDesc.size());
		//assertThat(resultDesc.size(), is(2));
		//assertThat(resultDesc, hasItems(account2, account3));
	}

	@Test
	public void findByFirstnameOrLastnameUsingSpEL() {

		User reference = new User();
		reference.setEmail(RandomStringUtils.randomAlphabetic(5));
		reference.setFirstName("firstname");
		reference.setLastName("lastname");

		Iterable<User> users = userRepository
				.findByFirstNameOrLastName(reference);
		
		Assert.assertNotNull(users);
		
		Assert.assertTrue(users.iterator().hasNext());

		//Assert.assertEquals(users, is(iterableWithSize(2)));
		//assertThat(users, hasItems(account, account2));
		
	}

}
