package com.common.repository;

import com.common.domain.Organizacion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrganizacionRepositoryCustomImpl extends CommonRepositoryAbstract<Organizacion> implements CommonRepositoryCustom<Organizacion>{

    public Page<Organizacion> findPageByParameters(String searchValue, Pageable pageable, Organizacion entity) {
        String where = searchLike(searchValue,Organizacion.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getStatus())){
            where = where+" AND o.status='"+entity.getStatus()+"' ";
        }
        if (entity!=null && StringUtils.isNotBlank(entity.getTipoOrganizacion())){
            where = where+" AND o.tipoOrganizacion='"+entity.getTipoOrganizacion()+"' ";
        }
        return super.getPage(Organizacion.class, where,super.getSortQuery(pageable), pageable);
    }
}
