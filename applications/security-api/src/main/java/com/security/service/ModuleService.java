/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Module;

/**
 * @author efrain.calla
 *
 */
public interface ModuleService extends BaseService<Module>{
    List<Module> findEnabledList();
    Module findByName(String name);
}
