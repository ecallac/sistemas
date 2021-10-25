/**
 * 
 */
package com.security.service;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface BaseService <T>{
    List<T> findList();
    T findById(Long id);
    void save(T entity);
}
