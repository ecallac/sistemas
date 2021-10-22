package com.common.enums;

public enum CodigoTypoBase {
	PERSONA("E_PERSONA"),
    ORGANIZACION("E_ORGANIZACION");
    private String code;

    private CodigoTypoBase(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
