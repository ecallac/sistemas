package com.common.repository;

import com.common.domain.Persona;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PersonaRepositoryCustomImpl extends CommonRepositoryAbstract<Persona> implements CommonRepositoryCustom<Persona>{

    public Page<Persona> findPageByParameters(String searchValue, Pageable pageable, Persona entity) {
        String where = searchLike(searchValue,Persona.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getStatus())){
            where = where+" AND o.status='"+entity.getStatus()+"' ";
        }
        if (entity!=null && StringUtils.isNotBlank(entity.getTipoDocumentoIdentificaion())){
            where = where+" AND o.tipoDocumentoIdentificaion='"+entity.getTipoDocumentoIdentificaion()+"' ";
        }
        if (entity!=null && StringUtils.isNotBlank(entity.getTipoEstadoCivil())){
            where = where+" AND o.tipoEstadoCivil='"+entity.getTipoEstadoCivil()+"' ";
        }
        return super.getPage(Persona.class, where,super.getSortQuery(pageable), pageable);
    }
}
