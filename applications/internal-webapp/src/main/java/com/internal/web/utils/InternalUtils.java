package com.internal.web.utils;

import com.DataTablesInput;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class InternalUtils {
    public static Map<String, Object> getParameterMap(HttpServletRequest request){
        Enumeration enumeration = request.getParameterNames();
        Map<String, Object> parameterMap = new HashMap<>();
        while(enumeration.hasMoreElements()){
            String parameterName = (String) enumeration.nextElement();
            parameterMap.put(parameterName, request.getParameter(parameterName));
        }
        return parameterMap;
    }

    public static DataTablesInput createDataTablesInput(Map<String, Object> parameterMap){
        DataTablesInput dataTablesInput = new DataTablesInput();
        dataTablesInput.setDraw((String) parameterMap.get("draw"));
        dataTablesInput.setStart((String) parameterMap.get("start"));
        dataTablesInput.setRowPerPage((String) parameterMap.get("length"));
        dataTablesInput.setColumnSortOrderDirection((String) parameterMap.get("order[0][dir]"));
        dataTablesInput.setSearchValue((String) parameterMap.get("search[value]"));
        dataTablesInput.setColumnName((String) parameterMap.get("columns["+(String) parameterMap.get("order[0][column]")+"][data]"));
        return dataTablesInput;
    }
}
