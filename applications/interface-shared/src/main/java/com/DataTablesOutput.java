package com;

import java.util.List;

public class DataTablesOutput <T>{
    String draw ;
    Integer recordsTotal ;
    Integer recordsFiltered ;
    List<T> data;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataTablesOutput{" +
                "draw='" + draw + '\'' +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }
}
