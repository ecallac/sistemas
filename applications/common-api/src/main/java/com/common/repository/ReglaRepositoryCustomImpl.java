package com.common.repository;

import com.common.domain.Regla;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReglaRepositoryCustomImpl extends CommonRepositoryAbstract<Regla> implements CommonRepositoryCustom<Regla>{

    public Page<Regla> findPageByParameters(String searchValue, Pageable pageable, Regla entity) {
        String where = searchLike(searchValue,Regla.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getActivo())){
            where = where+" AND o.activo='"+entity.getActivo()+"' ";
        }
        return super.getPage(Regla.class, where,super.getSortQuery(pageable), pageable);
    }
}
