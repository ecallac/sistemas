package com.common.repository;

import com.common.domain.ReglaDetalle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReglaDetalleRepositoryCustomImpl extends CommonRepositoryAbstract<ReglaDetalle> implements CommonRepositoryCustom<ReglaDetalle>{

    public Page<ReglaDetalle> findPageByParameters(String searchValue, Pageable pageable, ReglaDetalle entity) {
        String where =searchLike(searchValue,ReglaDetalle.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getActivo())){
            where = where+" AND o.activo='"+entity.getActivo()+"' ";
        }
        if (entity!=null && entity.getRegla()!=null && entity.getRegla().getId()!=null){
            where = where+" AND o.regla.id='"+entity.getRegla().getId()+"' ";
        }
        return super.getPage(ReglaDetalle.class, where,super.getSortQuery(pageable), pageable);
    }
}
