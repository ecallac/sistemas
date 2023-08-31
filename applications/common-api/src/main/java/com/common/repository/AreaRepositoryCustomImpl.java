package com.common.repository;

import com.common.domain.Area;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AreaRepositoryCustomImpl extends CommonRepositoryAbstract<Area> implements CommonRepositoryCustom<Area>{

    public Page<Area> findPageByParameters(String searchValue, Pageable pageable, Area entity) {
        String where = searchLike(searchValue,Area.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getActivo())){
            where = where+" AND o.activo='"+entity.getActivo()+"' ";
        }
        if (entity!=null && entity.getParentArea()!=null && entity.getParentArea().getId()!=null){
            where = where+" AND o.parentArea.id='"+entity.getParentArea().getId()+"' ";
        }
        return super.getPage(Area.class, where,super.getSortQuery(pageable), pageable);
    }
}
