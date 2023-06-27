package com;

public enum GeneralConstant {
    SUCCESS("Success"),
    ERROR("Error"),
    SWITH_LABEL_TIPE("SWITH_LABEL_TIPE"),
    USER_STATUS_LAVEL_TYPE("USER_STATUS_LAVEL_TYPE");
    private String code;

    private GeneralConstant(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
