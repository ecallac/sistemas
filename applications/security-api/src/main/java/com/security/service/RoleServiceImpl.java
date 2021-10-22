/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Permission;
import com.security.domain.Role;
import com.security.domain.RolePermission;
import com.security.domain.RoleUser;
import com.security.enums.EnableIndicator;
import com.security.repository.RolePermissionRepository;
import com.security.repository.RoleRepository;
import com.security.repository.RoleUserRepository;
import com.security.util.BeanParser;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleUserRepository roleUserRepository;
	
	@Autowired
	RolePermissionRepository rolePermissionRepository;
	
	/* (non-Javadoc)
	 * @see com.security.service.BaseService#findList()
	 */
	@Override
	public List<Role> findList() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.security.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.security.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Role entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Role enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (Role) BeanParser.parseBetweenObjects(entity, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            roleRepository.save(enrityFromDb);
        }else{
        	entity.setDateCreated(new Date());
        	roleRepository.save(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.security.service.BaseService#updateEnable(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateEnable(Long id, String updatedBy) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.security.service.BaseService#updateDisable(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateDisable(Long id, String updatedBy) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.security.service.RoleService#findEnabledList()
	 */
	@Override
	public List<Role> findEnabledList() {
		// TODO Auto-generated method stub
		return roleRepository.findAllByEnabledOrderByDescription(EnableIndicator.ENABLED.getCode());
	}

	@Override
	public List<Role> findListByPermissionId(Long permissionId) {
		// TODO Auto-generated method stub
		return getRolesByPermission(rolePermissionRepository.findByPermissionId(permissionId));
	}
	
	private List<Role> getRolesByPermission(List<RolePermission> rolePermissions){
		List<Role> roles = new ArrayList<Role>();
		for (RolePermission rolePermission : rolePermissions) {
			roles.add(rolePermission.getRole());
		}
		return roles;
	}
}
