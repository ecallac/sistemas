/**
 * 
 */
package com.security.repository;

import java.util.List;
import java.util.function.LongSupplier;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author efrain.calla
 *
 */
public abstract class CommonRepositoryAbstract<T> {
	@Autowired
    protected EntityManager entityManager;
	
	public String getSortQuery(Pageable pageable) {
		String sort = "";
		if (pageable.getSort()!=null) {
			String property = pageable.getSort().toList().get(0).getProperty();
			String dir = pageable.getSort().toList().get(0).getDirection().name();
			return " order by o."+property+" "+dir;
		}
		return sort;
	}
	
	@SuppressWarnings("unchecked")
	public Page<T> getPage(String query,String queryCount,Pageable pageable){
		List <T> fooList = entityManager.createQuery(query).setFirstResult((pageable.getPageNumber()) * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();
	    LongSupplier sup = () -> (long)entityManager.createQuery(queryCount).getSingleResult();
	    return PageableExecutionUtils.getPage(fooList, pageable,sup);
	}
}