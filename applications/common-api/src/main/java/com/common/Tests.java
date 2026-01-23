package com.common;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tests {
    public static boolean formatDate(String dateStr, String format) {
        try {
            System.out.println("dateStr = " + dateStr + ", format = " + format);
            DateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(dateStr);
            System.out.println("date = " + date + ", format = " + format);

            String val = df.format(date);
            System.out.println("val = " + val + ", format = " + format);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    public static void main(String[] args) {
        String dateStr = "2024-05-30T15:30:00+02:00";
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX";

        System.out.println("Is valid date: " + Tests.formatDate(dateStr, dateFormat));
    }
}