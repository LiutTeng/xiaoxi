package com.xiaoxi.study.common.exception;

/**
 * @author liuteng
 */
public class ServiceException extends Exception {

    private String code;

    private String message;

    public ServiceException(String code) {
        this.code = code;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
