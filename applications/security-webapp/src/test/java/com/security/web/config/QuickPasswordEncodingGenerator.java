/**
 * 
 */
package com.security.web.config;

/**
 * @author efrain.calla
 *
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class QuickPasswordEncodingGenerator {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
            String password = "asdasd";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode(password));
    }
 
}
