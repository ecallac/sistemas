/**
 * 
 */
package com.security.dao;

import java.util.List;

import com.security.dao.common.BaseDao;
import com.security.domain.Module;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 9:16:05
 */
public interface ModuleDao extends BaseDao {
	List<Module> findByEnabled(String enabled);
	Module findByName(String name);
}
