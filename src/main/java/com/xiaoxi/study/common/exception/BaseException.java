package com.xiaoxi.study.common.exception;

/**
 * @author liuteng
 */
public class BaseException extends Exception {

    private String code;

    private String message;

    public BaseException(String code) {
        this.code = code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
