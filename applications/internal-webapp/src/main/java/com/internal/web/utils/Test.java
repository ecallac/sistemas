package com.internal.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) {
        try {
            String originalString = "Hola, ¿cómo estás?";
            System.out.println("Original: " + originalString);
            String encodedString = URLEncoder.encode(originalString, "UTF-8");
            System.out.println("Encoded : " + encodedString);
            String decodedString = URLDecoder.decode(encodedString, "UTF-8");
            System.out.println("Decoded : " + decodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
