package com.common;

public class Column {
    private String name;
    private String typeName;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", typeName='" + typeName + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
