package com;

public class DataTablesInput<T> {
    String draw ;
    String start ;
    String rowPerPage ;
    String columnSortOrderDirection;
    String searchValue ;
    String columnName;
    T object;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(String rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public String getColumnSortOrderDirection() {
        return columnSortOrderDirection;
    }

    public void setColumnSortOrderDirection(String columnSortOrderDirection) {
        this.columnSortOrderDirection = columnSortOrderDirection;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "DataTablesInput{" +
                "draw='" + draw + '\'' +
                ", start='" + start + '\'' +
                ", rowPerPage='" + rowPerPage + '\'' +
                ", columnSortOrderDirection='" + columnSortOrderDirection + '\'' +
                ", searchValue='" + searchValue + '\'' +
                ", columnName='" + columnName + '\'' +
                ", object=" + object +
                '}';
    }
}
