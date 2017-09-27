/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Module;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 12:44:00
 */
public interface ModuleService {
	void save(Module module);
	void delete(Module module);
	Module findModuleById(Long id);
	List<Module> findAllModules();
	List<Module> findModulesByEnabled(String enabled);
	Module findByName(String name);
}
