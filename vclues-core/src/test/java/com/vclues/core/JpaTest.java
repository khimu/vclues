package com.vclues.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.repository.TestAppConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestAppConfig.class})
//@ImportResource({ "classpath*:spring/database.xml" })
public class JpaTest {

    @Test
    public void testDummy() {
    	System.out.println("hello");
    }
}