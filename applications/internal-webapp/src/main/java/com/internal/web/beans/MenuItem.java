package com.internal.web.beans;

import java.util.List;

public class MenuItem {
    String id;
    String descripcion;
    String url;
    boolean selected;
    List<MenuItem> menuItems;

    public MenuItem(String id, String descripcion, String url, boolean selected, List<MenuItem> menuItems) {
        this.id = id;
        this.descripcion = descripcion;
        this.url = url;
        this.selected = selected;
        this.menuItems = menuItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", url='" + url + '\'' +
                ", selected=" + selected +
                ", menuItems=" + menuItems +
                '}';
    }
}
