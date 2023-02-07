package com.common.repository;

import com.Utils;
import com.common.domain.Searchable;
import com.common.domain.TipoBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TipoBaseRepositoryCustomImpl extends CommonRepositoryAbstract<TipoBase> implements CommonRepositoryCustom<TipoBase>{

    public Page<TipoBase> findPageByParameters(String searchValue, Pageable pageable, TipoBase entity) {
        String where = searchLike(searchValue,TipoBase.class);
        if (entity!=null && StringUtils.isNotBlank(entity.getActivo())){
            where = where+" AND o.activo='"+entity.getActivo()+"' ";
        }
        return super.getPage(TipoBase.class, where,super.getSortQuery(pageable), pageable);
    }
}
