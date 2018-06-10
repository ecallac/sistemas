/**
 * 
 */
package com.security.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.security.domain.Module;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 22:48:09
 */
public class ModuleDaoTest extends AbstractUnitTest{
	
	@Autowired
	ModuleDao moduleDao;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAllUsers(){
		List<Module> modules = (List<Module>) moduleDao.findAll(Module.class);
		logger.info("#####"+modules);
	}
}
