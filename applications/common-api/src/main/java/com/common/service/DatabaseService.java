package com.common.service;

import com.common.Column;
import com.common.Table;

import java.util.List;

public interface DatabaseService {
    List<Table> findTables(String catalog) throws Exception;
    List<Column> findColumnsByTable(String table) throws Exception;
}
