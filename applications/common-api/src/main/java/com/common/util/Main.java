package com.common.util;

import com.common.domain.Empleado;
import com.common.domain.Searchable;
import com.common.domain.Sucursal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        obtenerCamposSearchable(Sucursal.class);
    }

    private static void obtenerCamposSearchable(Class<?> clase) {
        Map<String,Object> map = new HashMap<>();
        try {
            Field[] campos = clase.getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);
                String nombreCampo = campo.getName();
                Class<?> tipoCampo = campo.getType();
                if (tipoCampo.getTypeName().contains("domain")){
                    Field personaField = clase.getDeclaredField(nombreCampo);
//                    System.out.println("3Clase: " + clase.getSimpleName() + ", Campo: " + personaField.getName());
                    Field[] campos1 = tipoCampo.getDeclaredFields();
                    for (Field campo1 : campos1) {
                        campo1.setAccessible(true);
                        if (campo1.isAnnotationPresent(Searchable.class)) {
//                            System.out.println("1Clase: " + clase.getSimpleName() + ", Campo: " + nombreCampo+"."+campo1.getName()+", Tipo: "+campo1.getType());
                            map.put(nombreCampo+"."+campo1.getName(),null);
                        }
                    }
                }
                if (campo.isAnnotationPresent(Searchable.class)) {
//                    System.out.println("1Clase: " + clase.getSimpleName() + ", Campo: " + campo.getName()+", Tipo: "+campo.getType());
                    map.put(campo.getName(),null);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            list.add(entry.getKey());
        }
        list.sort((elemento1, elemento2) -> elemento1.compareTo(elemento2));
        list.forEach(System.out::println);
    }
}
