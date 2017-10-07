/**
 * 
 */
package com.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.BeanParser;
import com.security.dao.PermissionDao;
import com.security.domain.Module;
import com.security.domain.Permission;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 12:47:44
 */
@Transactional
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionDao permissionDao;
	@Autowired
	ModuleService moduleService;
	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#save(com.security.domain.Module)
	 */
	
	public void save(Permission permission) {
		if (permission.getId()==null) {
			permissionDao.save(permission);
		}else{
			Permission permissionStored = findPermissionById(permission.getId());
			if (permissionStored!=null) {
				permissionStored = (Permission) BeanParser.parseBetweenObjects(permission, permissionStored, null);
				
				if (permission.getParentPermission()!=null) {
					Permission parentPermissionStored = findPermissionById(permission.getParentPermission().getId());
					permissionStored.setParentPermission(parentPermissionStored);
				}else{
					permissionStored.setParentPermission(null);
				}
				
				if (permission.getModule()!=null) {
					Module moduleStored = moduleService.findModuleById(permission.getModule().getId());
					permissionStored.setModule(moduleStored);
				}
				permissionDao.save(permissionStored);
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#delete(com.security.domain.Module)
	 */
	public void delete(Permission permission) {
		permissionDao.delete(permission);

	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findById(java.lang.Long)
	 */
	public Permission findPermissionById(Long id) {
		// TODO Auto-generated method stub
		return (Permission) permissionDao.findById(Permission.class, id);
	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> findAllPermissions() {
		// TODO Auto-generated method stub
		return (List<Permission>) permissionDao.findAll(Permission.class);
	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findByStatusId(java.lang.Long)
	 */
	public List<Permission> findPermissionsByEnabled(String enabled) {
		// TODO Auto-generated method stub
		return permissionDao.findByEnabled(enabled);
	}

	public List<Permission> findPermissionsByModuleId(Long id) {
		// TODO Auto-generated method stub
		return permissionDao.findByModuleId(id);
	}

	public List<Permission> findPermissionByRoleId(Long id) {
		// TODO Auto-generated method stub
		return permissionDao.findPermissionByRoleId(id);
	}

	@Override
	public List<Permission> findEnabledPermissionsByModuleId(Long id) {
		// TODO Auto-generated method stub
		return permissionDao.findEnabledPermissionsByModuleId(id);
	}

}
