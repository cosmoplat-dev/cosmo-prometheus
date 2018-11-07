package com.haier.prometheus.utils;

public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(0),
    /**
     * 失败
     */
    FAILURE(1),
    /**
     * 系统异常
     */
    EXCEPTION(2);

    private int code;
    private ResponseCode(int code){
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

}
