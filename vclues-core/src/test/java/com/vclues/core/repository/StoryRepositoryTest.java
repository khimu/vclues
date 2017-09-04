package com.vclues.core.repository;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.service.IStoryService;

@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = "com.vclues.core.repository")
public class StoryRepositoryTest {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private IStoryService storyService;
	
	private User user1;
	private User user2;
	private User user3;

	private String emailConfirmation;
	
	private String resetPasswordConfirmation;

	@Before
	public void setUp() {		

	}

	@After
	public void destroy() {
	}
	
	@Test
	public void testStoryCache() {
		Story story = new Story();
		story.setTitle("khim");
		story.setAnswer("khim ung");
		
		Story savedStory = storyService.saveStory(story);
		
		Story storyGet1 = storyService.getStory(savedStory.getId());
		
		System.out.println("should be silent below");
		
		Story storyGet2 = storyService.getStory(savedStory.getId());
	}
	
}
