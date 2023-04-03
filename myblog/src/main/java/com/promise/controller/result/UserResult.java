package com.promise.controller.result;

/**
 * 向前端返回的结果
 */
public class UserResult {
    private Integer code;
    private String msg;
    private String token;

    public UserResult(Integer code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }

    public UserResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
