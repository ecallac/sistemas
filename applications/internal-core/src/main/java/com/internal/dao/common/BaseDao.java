/**
 * 
 */
package com.internal.dao.common;

import java.util.List;

import com.internal.domain.BaseEntity;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 8:36:48
 */
public interface BaseDao {
	void save(BaseEntity baseEntity);
	void deleteList(List<BaseEntity> baseEntities);
	void delete(BaseEntity baseEntity);
	Object findById(Class<?> classObject, Long id);
	List<?> findAll(Class<?> classObject);
}
