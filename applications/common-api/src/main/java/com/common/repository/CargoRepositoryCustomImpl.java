package com.common.repository;

import com.common.domain.Cargo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CargoRepositoryCustomImpl extends CommonRepositoryAbstract<Cargo> implements CommonRepositoryCustom<Cargo>{

    public Page<Cargo> findPageByParameters(String searchValue, Pageable pageable, Cargo entity) {
        String where = searchLike(searchValue,Cargo.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getActivo())){
            where = where+" AND o.activo='"+entity.getActivo()+"' ";
        }
        return super.getPage(Cargo.class, where,super.getSortQuery(pageable), pageable);
    }
}
