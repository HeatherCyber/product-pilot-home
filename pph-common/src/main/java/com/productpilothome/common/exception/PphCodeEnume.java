package com.productpilothome.common.exception;

/**
 * @author Heather
 * @version 1.0
 */
public enum PphCodeEnume {

    UNKNOWN_EXCEPTION(40000, "System unknown exception"),
    INVALID_EXCEPTION(40001, "Parameter validation exception");
    private int code;
    private String msg;

    PphCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
