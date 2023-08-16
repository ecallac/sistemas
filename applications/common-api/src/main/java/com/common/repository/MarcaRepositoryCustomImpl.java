package com.common.repository;

import com.common.domain.Marca;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MarcaRepositoryCustomImpl extends CommonRepositoryAbstract<Marca> implements CommonRepositoryCustom<Marca>{

    public Page<Marca> findPageByParameters(String searchValue, Pageable pageable, Marca entity) {
        String where =searchLike(searchValue,Marca.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getStatus())){
            where = where+" AND o.status='"+entity.getStatus()+"' ";
        }
        return super.getPage(Marca.class, where,super.getSortQuery(pageable), pageable);
    }
}
