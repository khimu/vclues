package com.vclues.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vclues.app.Application;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")  // reference test.properties
public class ApplicationTests {
	private final static Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	@Test
	public void contextLoads() {
		logger.info("hello test");
	}

}
  