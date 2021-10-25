/**
 * 
 */
package com.security.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Module;
import com.security.enums.EnableIndicator;
import com.security.repository.ModuleRepository;
import com.security.util.BeanParser;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public List<Module> findList() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findById(Long id) {
        return moduleRepository.findById(id).get();
    }

    @Override
    public void save(Module entity) {
        if (entity.getId()!=null){
            Module enrityFromDb = findById(entity.getId());
            entity.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (Module) BeanParser.parseBetweenObjects(entity, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            moduleRepository.save(enrityFromDb);
        }else{
        	entity.setDateCreated(new Date());
            moduleRepository.save(entity);
        }
    }

    @Override
    public List<Module> findEnabledList() {
        return moduleRepository.findAllByEnabledOrderByDescription(EnableIndicator.ENABLED.getCode());
    }

	@Override
	public Module findByName(String name) {
		return moduleRepository.findFirstByName(name);
	}
}