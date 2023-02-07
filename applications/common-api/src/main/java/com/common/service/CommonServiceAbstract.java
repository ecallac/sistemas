package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.logging.Logger;

public abstract class CommonServiceAbstract<T> {
    Logger logger = Logger.getLogger(CommonServiceAbstract.class.getName());
    int getPage(String start, String rowPerPage) {
        if (Integer.parseInt(rowPerPage)<0) {
            return 0;
        } else {
            return (Integer.parseInt(start)/Integer.parseInt(rowPerPage));
        }
    }
    int getRowSize(String start, String rowPerPage) {
        if (Integer.parseInt(rowPerPage)<0) {
            return Integer.MAX_VALUE;
        } else {
            return Integer.parseInt(rowPerPage);
        }
    }
    DataTablesOutput<T> getSearchedElements(Page<T> page, Long totalElements){
        Long totalElementsSearched=page.getTotalElements();
        List<T> permissions= page.getContent();
        DataTablesOutput<T> dataTablesOutput = new DataTablesOutput<T>();
        dataTablesOutput.setRecordsTotal(totalElements.intValue());
        dataTablesOutput.setRecordsFiltered(totalElementsSearched.intValue());
        dataTablesOutput.setData(permissions);
        return dataTablesOutput;
    }
    Pageable getPageRequest(DataTablesInput<T> dataTablesInput) {
        return PageRequest.of(getPage(dataTablesInput.getStart(), dataTablesInput.getRowPerPage()),getRowSize(dataTablesInput.getStart(), dataTablesInput.getRowPerPage()),(dataTablesInput.getColumnSortOrderDirection().equalsIgnoreCase("asc")? Sort.by(dataTablesInput.getColumnName()).ascending():Sort.by(dataTablesInput.getColumnName()).descending()));
    }
}
