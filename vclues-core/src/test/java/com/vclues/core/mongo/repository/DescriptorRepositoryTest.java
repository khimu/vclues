package com.vclues.core.mongo.repository;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vclues.core.data.Announcement;
import com.vclues.core.data.DataField;
import com.vclues.core.data.Descriptor;
import com.vclues.core.repository.TestAppConfig;


@SpringBootTest(classes={TestAppConfig.class})
@RunWith(SpringRunner.class)
public class DescriptorRepositoryTest {

	@Autowired
	private AnnouncementRepository announcementService;
	
	private Announcement announcement;
	
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
