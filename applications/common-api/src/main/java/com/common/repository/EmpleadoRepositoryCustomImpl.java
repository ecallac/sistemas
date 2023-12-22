package com.common.repository;

import com.common.domain.Empleado;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class EmpleadoRepositoryCustomImpl extends CommonRepositoryAbstract<Empleado> implements CommonRepositoryCustom<Empleado>{

    public Page<Empleado> findPageByParameters(String searchValue, Pageable pageable, Empleado entity) {
        String where = searchLike(searchValue,Empleado.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getEstado())){
            where = where+" AND o.activo='"+entity.getEstado()+"' ";
        }
        return super.getPage(Empleado.class, where,super.getSortQuery(pageable), pageable);
    }
}
