package com.common.repository;

import com.common.domain.Componente;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ComponenteRepositoryCustomImpl extends CommonRepositoryAbstract<Componente> implements CommonRepositoryCustom<Componente>{

    public Page<Componente> findPageByParameters(String searchValue, Pageable pageable, Componente entity) {
        String where =searchLike(searchValue,Componente.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getStatus())){
            where = where+" AND o.status='"+entity.getStatus()+"' ";
        }
        if (entity!=null && StringUtils.isNotBlank(entity.getTipoComponnte())){
            where = where+" AND o.tipoComponente='"+entity.getTipoComponnte()+"' ";
        }
        return super.getPage(Componente.class, where,super.getSortQuery(pageable), pageable);
    }
}
