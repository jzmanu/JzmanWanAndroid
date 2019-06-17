package com.manu.wanandroid.http.exception;

/**
 * @Desc: MException
 * @Author: jzman
 * @Date: 2019/5/10 0010 15:06
 */
public class MException extends RuntimeException {
    private int errorCode = -1;
    private String errorMessage;

    public MException() {
    }

    public MException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public MException(int code, String message) {
        super(message);
        this.errorCode = code;
        this.errorMessage = message;
    }

    public MException(Throwable cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "MException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
