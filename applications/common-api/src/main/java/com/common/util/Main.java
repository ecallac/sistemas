package com.common.util;

import com.common.domain.Empleado;
import com.common.domain.Searchable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        obtenerCamposSearchable(Empleado.class);
    }

    private static void obtenerCamposSearchable(Class<?> clase) {
        Field[] campos = clase.getDeclaredFields();

        for (Field campo : campos) {
            if (campo.isAnnotationPresent(Searchable.class)) {
                Searchable searchableAnnotation = campo.getAnnotation(Searchable.class);
                System.out.println("1Clase: " + clase.getSimpleName() + ", Campo: " + campo.getName()+" - "+campo.getType());
            }


        }

        // Verificar también en la clase Persona si el campo es de tipo Persona
        if (clase.equals(Empleado.class)) {
            Field personaField;
            try {
                personaField = Empleado.class.getDeclaredField("persona");
                if (personaField.isAnnotationPresent(Searchable.class)) {
                    Searchable searchableAnnotation = personaField.getAnnotation(Searchable.class);
                    System.out.println("2Clase: " + clase.getSimpleName() + ", Campo: " + personaField.getName());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
