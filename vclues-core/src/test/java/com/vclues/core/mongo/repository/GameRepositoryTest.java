package com.vclues.core.mongo.repository;

import java.util.List;

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
@EnableMongoRepositories(basePackages = "com.vclues.core.mongo.repository")
public class GameRepositoryTest {

	@Autowired
	private GameRepository gameService;
	
	private Game game;
	
	@Test
	public void findGamesByEmailAndDone() {
		List<Game> games = gameService.findGamesByEmailAndDone( "kim2kim@gmail.com", false); 
		if(games == null) {
			System.out.println("No games");
		}
		else {
			System.out.println("has games " + games.size());
			for(Game game : games) {
				System.out.println("game " + game.getId());
			}
		}
	}
	
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
