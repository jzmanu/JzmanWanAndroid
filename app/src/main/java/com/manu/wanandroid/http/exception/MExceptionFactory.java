package com.manu.wanandroid.http.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @Desc: MExceptionFactory
 * @Author: jzman
 * @Date: 2019/5/10 0010 15:18
 */
public class MExceptionFactory {

    private static final String MESSAGE_HTTP_EXCEPTION = "网络错误";
    private static final String MESSAGE_CONNECTION_EXCEPTION = "连接失败";
    private static final String MESSAGE_CONNECTION_TIMEOUT_EXCEPTION = "连接超时";
    private static final String MESSAGE_PARSE_EXCEPTION = "解析出错";
    private static final String MESSAGE_UNKNOWN_HOST_EXCEPTION = "无法解析该域名";
    private static final String MESSAGE_UNKNOWN_EXCEPTION = "未知错误";
    private static final String MESSAGE_DATA_WR_EXCEPTION = "数据读写错误";

    private static final String MESSAGE_REQUEST_EXPIRE = "登录过期";
    private static final String MESSAGE_NO_PERMISSION = "没有权限";
    private static final String MESSAGE_UNKNOWN_REQUEST = "未知请求";
    private static final String MESSAGE_REQUEST_ERROR = "请求错误";
    private static final String MESSAGE_SERVER_INNER_ERROR = "服务器内部错误";
    private static final String MESSAGE_SERVER_CANNOT_IDENTIFY_REQUEST = "无法识别该请求";
    private static final String MESSAGE_SERVER_BAD_GATEWAY = "错误网关";
    private static final String MESSAGE_SERVER_CANNOT_AVAILABLE = "服务不可用";
    private static final String MESSAGE_SERVER_GATEWAY_TIMEOUT = "网关超时";
    private static final String MESSAGE_SERVER_EXCEPTION = "服务器异常";

    private static final int CODE_HTTP_EXCEPTION = 0x1;
    private static final int CODE_CONNECTION_EXCEPTION = 0x2;
    private static final int CODE_CONNECTION_TIMEOUT_EXCEPTION = 0x3;
    private static final int CODE_PARSE_EXCEPTION = 0x4;
    private static final int CODE_UNKNOWN_HOST_EXCEPTION = 0x5;
    private static final int CODE_UNKNOWN_EXCEPTION = 0x6;
    private static final int CODE_DATA_WR_EXCEPTION = 0x6;

    private static final int CODE_REQUEST_EXPIRE = 0x7;
    private static final int CODE_NO_PERMISSION = 0x8;
    private static final int CODE_UNKNOWN_REQUEST = 0x9;
    private static final int CODE_REQUEST_ERROR = 0x10;
    private static final int CODE_SERVER_INNER_ERROR = 0x11;
    private static final int CODE_SERVER_CANNOT_IDENTIFY_REQUEST = 0x12;
    private static final int CODE_SERVER_BAD_GATEWAY = 0x13;
    private static final int CODE_SERVER_CANNOT_AVAILABLE = 0x14;
    private static final int CODE_SERVER_GATEWAY_TIMEOUT = 0x15;
    private static final int CODE_SERVER_EXCEPTION = 0x16;

    public static MException createMException(Throwable e) {
        System.out.println("createMException--->" + e.toString());
        MException mException = new MException(e);
        if (e instanceof HttpException) {
            //网络错误
//            mException.setErrorCode(CODE_HTTP_EXCEPTION);
//            mException.setErrorMessage(MESSAGE_HTTP_EXCEPTION);
            mException = createHttpCodeMException(((HttpException) e).code());
        } else if (e instanceof ConnectException || e instanceof SocketException) {
            //连接失败
            mException.setErrorCode(CODE_CONNECTION_EXCEPTION);
            mException.setErrorMessage(MESSAGE_CONNECTION_EXCEPTION);
        } else if (e instanceof SocketTimeoutException) {
            //连接超时
            mException.setErrorCode(CODE_CONNECTION_TIMEOUT_EXCEPTION);
            mException.setErrorMessage(MESSAGE_CONNECTION_TIMEOUT_EXCEPTION);
        } else if (e instanceof JsonParseException || e instanceof ParseException) {
            //解析出错
            mException.setErrorCode(CODE_PARSE_EXCEPTION);
            mException.setErrorMessage(MESSAGE_PARSE_EXCEPTION);
        } else if (e instanceof UnknownHostException) {
            //域名无法解析
            mException.setErrorCode(CODE_UNKNOWN_HOST_EXCEPTION);
            mException.setErrorMessage(MESSAGE_UNKNOWN_HOST_EXCEPTION);
        } else if (e instanceof MException) {
            //自定义异常
            mException.setErrorCode(((MException) e).getErrorCode());
            mException.setErrorMessage(((MException) e).getErrorMessage());
        } else if (e instanceof IOException) {
            //数据读写错误
            mException.setErrorCode(CODE_DATA_WR_EXCEPTION);
            mException.setErrorMessage(MESSAGE_DATA_WR_EXCEPTION);
        } else {
            //未知错误
            mException.setErrorCode(CODE_UNKNOWN_EXCEPTION);
            mException.setErrorMessage(MESSAGE_UNKNOWN_EXCEPTION);
        }
        return mException;
    }

    public static MException createHttpCodeMException(int stateCode) {
        MException mException = new MException(CODE_UNKNOWN_EXCEPTION, MESSAGE_UNKNOWN_EXCEPTION);
        if (stateCode >= 400 && stateCode < 500) {
            if (stateCode == 401) {
                mException = new MException(CODE_REQUEST_EXPIRE, MESSAGE_REQUEST_EXPIRE);
            } else if (stateCode == 403) {
                mException = new MException(CODE_NO_PERMISSION, MESSAGE_NO_PERMISSION);
            } else if (stateCode == 404) {
                mException = new MException(CODE_UNKNOWN_REQUEST, MESSAGE_UNKNOWN_REQUEST);
            } else {
                mException = new MException(CODE_REQUEST_ERROR, MESSAGE_REQUEST_ERROR);
            }
        } else if (stateCode > 499) {
            if (stateCode == 500) {
                mException = new MException(CODE_SERVER_INNER_ERROR, MESSAGE_SERVER_INNER_ERROR);
            } else if (stateCode == 501) {
                mException = new MException(CODE_SERVER_CANNOT_IDENTIFY_REQUEST, MESSAGE_SERVER_CANNOT_IDENTIFY_REQUEST);
            } else if (stateCode == 502) {
                mException = new MException(CODE_SERVER_BAD_GATEWAY, MESSAGE_SERVER_BAD_GATEWAY);
            } else if (stateCode == 503) {
                mException = new MException(CODE_SERVER_CANNOT_AVAILABLE, MESSAGE_SERVER_CANNOT_AVAILABLE);
            } else if (stateCode == 504) {
                mException = new MException(CODE_SERVER_GATEWAY_TIMEOUT, MESSAGE_SERVER_GATEWAY_TIMEOUT);
            } else {
                mException = new MException(CODE_SERVER_EXCEPTION, MESSAGE_SERVER_EXCEPTION);
            }
        }
        return mException;
    }
}
