package com.promise.controller;

public class Code {
    // 登录成功
    public static final Integer LOGIN_SUC = 10001;
    // 登陆失败
    public static final Integer LOGIN_ERR = 10000;
    // 查询成功
    public static final Integer SELECT_SUC = 30001;
    // 查询失败
    public static final Integer SELECT_ERR = 30000;
    // 修改成功
    public static final Integer UPDATE_SUC = 40001;
    // 修改失败
    public static final Integer UPDATE_ERR = 40000;
    // 添加成功
    public static final Integer ADD_SUC = 50001;
    // 添加失败
    public static final Integer ADD_ERR = 50000;
    // 删除成功
    public static final Integer DELETE_SUC = 60001;
    // 删除失败
    public static final Integer DELETE_ERR = 60000;
    // 注册成功
    public static final Integer LOGON_SUC = 20001;
    // 注册失败
    public static final Integer LOGON_ERR = 20000;

    // 其他异常
    public static final Integer SYSTEM_UNKNOWN_ERR = 79999;
    // 系统异常
    public static final Integer SYSTEM_ERR = 70000;
    // 业务异常
    public static final Integer BUSINESS_ERR = 70001;
    // token验证失败
    public static final Integer TOKEN_ER = 39999;


    private Code() {
    }
}
