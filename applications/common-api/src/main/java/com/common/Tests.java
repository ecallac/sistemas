package com.common;

import com.Utils;
import com.common.domain.Searchable;
import com.common.domain.TipoBase;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Tests {
    public static void main(String args []){
        String searchValue = "asd";
        List<String> list = Utils.getFieldsWithAnnotationFromEntity(TipoBase.class,Searchable.class);
        list.replaceAll(l-> ("o."+l+" like '%"+searchValue+"%'"));
        String filter = StringUtils.join(list," or ");
        System.out.println(filter);
    }
}
