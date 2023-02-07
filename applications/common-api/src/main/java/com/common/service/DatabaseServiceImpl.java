package com.common.service;

import com.common.Column;
import com.common.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService{
    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Override
    public List<Table> findTables(String catalog) throws Exception {
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection established......");
        DatabaseMetaData metaData = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet tables = metaData.getTables(catalog, null, "%", types);
        List<com.common.Table> list = new ArrayList<>();
        while (tables.next()){
            String type = tables.getString("TABLE_TYPE");
            if ("TABLE".equalsIgnoreCase(type)){
                Table table = new Table();
                table.setCatalog(tables.getString("TABLE_CAT"));
                table.setSchema(tables.getString("TABLE_SCHEM"));
                table.setName(tables.getString("TABLE_NAME"));
                list.add(table);
            }

        }
        return list;
    }

    @Override
    public List<Column> findColumnsByTable(String table) throws Exception {
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM "+table);
        ResultSetMetaData md = rs.getMetaData();
        List<Column> list = new ArrayList<>();
        for (int i = 1; i <= md.getColumnCount(); i++) {
            Column column = new Column();
            column.setName(md.getColumnName(i));
            column.setTypeName(md.getColumnTypeName(i));
            column.setSize(md.getColumnDisplaySize(i));
            list.add(column);
        }
        return list;
    }
}
