package com.security.service;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 21:16:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/security/service/config/application-context-service-test.xml"})

public class AbstractUnitTest{
	protected static Logger logger = Logger.getLogger(AbstractUnitTest.class);
	
	@Test
	public void testOk() throws Exception {
		Assert.assertTrue(true);
	}
}
