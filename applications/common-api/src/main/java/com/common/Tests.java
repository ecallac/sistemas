package com.common;

public class Tests {
    public static void main(String args []){
        String searchValue = "Gerente General + '";
//        List<String> list = Utils.getFieldsWithAnnotationFromEntity(TipoBase.class,Searchable.class);
//        list.replaceAll(l-> ("o."+l+" like '%"+searchValue+"%'"));
//        String filter = StringUtils.join(list," or ");
//        System.out.println(filter);
        try {
            System.out.println(searchValue.replace(" ","%20"));
        }catch (Exception e){

        }

    }
}
