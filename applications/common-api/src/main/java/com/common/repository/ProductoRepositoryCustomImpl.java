package com.common.repository;

import com.common.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductoRepositoryCustomImpl extends CommonRepositoryAbstract<Producto> implements CommonRepositoryCustom<Producto> {

	@Override
	public Page<Producto> findPageByParameters(String searchValue, Pageable pageable, Producto entity) {
		String where = searchLike(searchValue, Producto.class);
		return super.getPage(Producto.class, where, super.getSortQuery(pageable), pageable);
	}
}
