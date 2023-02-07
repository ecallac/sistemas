package com.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonRepositoryCustom <T>{
    Page<T> findPageByParameters(String searchValue, Pageable pageable, T entity);
}
