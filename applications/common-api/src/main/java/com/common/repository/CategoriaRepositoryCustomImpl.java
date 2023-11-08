package com.common.repository;

import com.common.domain.Categoria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoriaRepositoryCustomImpl extends CommonRepositoryAbstract<Categoria> implements CommonRepositoryCustom<Categoria>{

    public Page<Categoria> findPageByParameters(String searchValue, Pageable pageable, Categoria entity) {
        String where = searchLike(searchValue,Categoria.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getStatus())){
            where = where+" AND o.activo='"+entity.getStatus()+"' ";
        }
        if (entity!=null && entity.getCategoriapadre()!=null && entity.getCategoriapadre().getId()!=null){
            where = where+" AND o.categoriapadre.id='"+entity.getCategoriapadre().getId()+"' ";
        }
        return super.getPage(Categoria.class, where,super.getSortQuery(pageable), pageable);
    }
}
