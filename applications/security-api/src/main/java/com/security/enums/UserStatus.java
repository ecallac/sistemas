/**
 * 
 */
package com.security.enums;

/**
 * @author efrain.calla
 *
 */
public enum UserStatus {
	NEW("S_NEW"),
    ACTIVE("S_ACTIVE"),
    INACTIVE("S_INACTIVE");
    private String code;

    private UserStatus(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
