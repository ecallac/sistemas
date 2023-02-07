package com.common;

public class Table {
    private String catalog;
    private String schema;
    private String name;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Table{" +
                "catalog='" + catalog + '\'' +
                ", schema='" + schema + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
