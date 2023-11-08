package com.common.repository;

import com.common.domain.Sucursal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SucursalRepositoryCustomImpl extends CommonRepositoryAbstract<Sucursal> implements CommonRepositoryCustom<Sucursal>{

    public Page<Sucursal> findPageByParameters(String searchValue, Pageable pageable, Sucursal entity) {
        String where = searchLike(searchValue,Sucursal.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getEstado())){
            where = where+" AND o.estado='"+entity.getEstado()+"' ";
        }
        if (entity!=null && StringUtils.isNotBlank(entity.getTiposucursal())){
            where = where+" AND o.tiposucursal='"+entity.getTiposucursal()+"' ";
        }
        if (entity!=null && entity.getOrganizacion()!=null && entity.getOrganizacion().getId()!=null){
            where = where+" AND o.organizacion.id='"+entity.getOrganizacion().getId()+"' ";
        }
        return super.getPage(Sucursal.class, where,super.getSortQuery(pageable), pageable);
    }
}
