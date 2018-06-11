/**
 * 
 */
package com.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.ModuleDao;
import com.security.domain.Module;
import com.security.utils.BeanParser;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 12:47:44
 */
@Transactional
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleDao moduleDao;
	
	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#save(com.security.domain.Module)
	 */
	public void save(Module module) {
		if (module.getId()==null) {
			moduleDao.save(module);
		}else{
			Module moduleStored = (Module) findModuleById(module.getId());
			if (moduleStored!=null) {
				if (!moduleStored.getEnabled().equals(module.getEnabled())) {
					moduleStored.setEnabled(module.getEnabled());
					moduleStored.setUpdatedBy(module.getUpdatedBy());
				} else {
					moduleStored = (Module) BeanParser.parseBetweenObjects(module, moduleStored, null);
				}
				moduleDao.save(moduleStored);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#delete(com.security.domain.Module)
	 */
	public void delete(Module module) {
		moduleDao.delete(module);

	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findById(java.lang.Long)
	 */
	public Module findModuleById(Long id) {
		// TODO Auto-generated method stub
		return (Module) moduleDao.findById(Module.class, id);
	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Module> findAllModules() {
		// TODO Auto-generated method stub
		return (List<Module>) moduleDao.findAll(Module.class);
	}

	/* (non-Javadoc)
	 * @see com.security.service.ModuleService#findByStatusId(java.lang.Long)
	 */
	public List<Module> findModulesByEnabled(String enabled) {
		// TODO Auto-generated method stub
		return moduleDao.findByEnabled(enabled);
	}

	public Module findByName(String name) {
		// TODO Auto-generated method stub
		return moduleDao.findByName(name);
	}

	

}
