/**
 * 
 */
package com.common.enums;

/**
 * @author efrain.calla
 *
 */
public enum Messages {
    ROWS_NOT_FOUND("Rows was not found in the database!"),
    RECORD_SAVED_SUCCESS("Your record have been saved successfully"),
	INTERNAL_ERROR("There is an internal Error");
    private String code;

    private Messages(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
