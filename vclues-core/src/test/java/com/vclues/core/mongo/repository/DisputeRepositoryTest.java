package com.vclues.core.mongo.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.data.Game;
import com.vclues.core.repository.TestAppConfig;


@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
@EnableMongoRepositories(basePackages = "com.nordic.core.mongo.repository")
public class DisputeRepositoryTest {

	@Autowired
	private GameRepository gameService;
	
	private Game game;
	
	@Before
	public void setup() {

	}
	
	@After
	public void destroy() {

	}
	
	@Test
	public void testFindAll() {

	}
	
	@Test(expected=Exception.class)
	public void testDuplicateKeyFails() {

	}
	
	@Test
	public void testNoDuplicate() {

	}
	
	@Test
	public void testFindByName() {

	}
	
	@Test
	public void testFindByNameFails() {

	}

}
