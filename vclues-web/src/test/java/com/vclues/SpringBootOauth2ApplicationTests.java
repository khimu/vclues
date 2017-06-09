package com.vclues;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vclues.SpringBootOauth2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringBootOauth2Application.class})
@WebAppConfiguration
public class SpringBootOauth2ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
