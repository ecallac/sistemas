/**
 * 
 */
package com.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author efrain.calla
 *
 */
public interface CommonRepositoryCustom<T> {
	Page<T> findPageByParameters(String searchValue,Pageable pageable,T object);
}