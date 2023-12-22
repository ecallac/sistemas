package com.common.repository;

import com.Utils;
import com.common.domain.Searchable;
import com.common.domain.TipoBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.LongSupplier;

public abstract class CommonRepositoryAbstract<T> {
    @Autowired
    protected EntityManager entityManager;
    private Logger logger = Logger.getLogger(this.getClass());

    public String getSortQuery(Pageable pageable) {
        String sort = "";
        if (pageable.getSort()!=null) {
            String property = pageable.getSort().toList().get(0).getProperty();
            String dir = pageable.getSort().toList().get(0).getDirection().name();
            return " order by o."+property+" "+dir;
        }
        return sort;
    }

    @SuppressWarnings("unchecked")
    public Page<T> getPage(Class rowClass, String where,String sort,Pageable pageable){
        String queryStr = "SELECT o FROM "+rowClass.getSimpleName()+" o WHERE 1=1 "+where+" "+sort;
        String queryStrCnt = "SELECT count(o) FROM "+rowClass.getSimpleName()+" o WHERE 1=1 "+where+" "+sort;
        List <T> fooList = entityManager.createQuery(queryStr).setFirstResult((pageable.getPageNumber()) * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();
        LongSupplier sup = () -> (long)entityManager.createQuery(queryStrCnt).getSingleResult();
        return PageableExecutionUtils.getPage(fooList, pageable,sup);
    }
    /**
     * Make filter with like for fields that have @Searchable annotation
     */
    public String searchLike(String searchValue,Class entity){
        if (StringUtils.isNotBlank(searchValue)) {
            List<String> fields = Utils.getFieldsWithAnnotationFromEntity(entity, Searchable.class);
            fields.replaceAll(l-> ("o."+l+" like '%"+searchValue+"%'"));
            String filter = StringUtils.join(fields," or ");
            return " AND ("+filter+") ";
        }
        return "";
    }
}
