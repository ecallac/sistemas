/**
 * 
 */
package com.security.dao;

import java.util.List;

import com.security.dao.common.BaseDao;
import com.security.domain.Permission;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 9:16:05
 */
public interface PermissionDao extends BaseDao {
	List<Permission> findByStatusId(Long id);
	List<Permission> findByModuleId(Long id);
	List<Permission> findPermissionByRoleId(Long id);
}
