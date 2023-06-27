package com.internal.web.view;

import com.common.Direccion;

public class DireccionView extends Direccion {
    String esprincipalStyle;
    String ubicaionTotal;

    public String getEsprincipalStyle() {
        return esprincipalStyle;
    }

    public void setEsprincipalStyle(String esprincipalStyle) {
        this.esprincipalStyle = esprincipalStyle;
    }

    public String getUbicaionTotal() {
        return ubicaionTotal;
    }

    public void setUbicaionTotal(String ubicaionTotal) {
        this.ubicaionTotal = ubicaionTotal;
    }

    @Override
    public String toString() {
        return "DireccionView{" +
                "esprincipalStyle='" + esprincipalStyle + '\'' +
                ", ubicaionTotal='" + ubicaionTotal + '\'' +
                '}';
    }
}
