/**
 * 
 */
package com.security.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.security.domain.Permission;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 22:48:09
 */
public class PermissionDaoTest extends AbstractUnitTest{
	
	@Autowired
	PermissionDao permissionDao;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAllUsers(){
		List<Permission> permissions = (List<Permission>) permissionDao.findAll(Permission.class);
		for (Permission permission : permissions) {
			logger.info("#####"+permission.getPath());
		}
		
	}
	
	@Test
	public void testfindPermissionByRoleId(){
		List<Permission> permissions = (List<Permission>) permissionDao.findPermissionByRoleId(1L);
		for (Permission permission : permissions) {
			logger.info("#####"+permission.getPath());
		}
		
	}
}
