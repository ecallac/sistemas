/**
 * 
 */
package com.common.enums;

/**
 * @author efrain.calla
 *
 */
public enum EnableIndicator {
    ENABLED("ENABLED"),
    DISABLED("DISABLED");
    private String code;

    private EnableIndicator(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
