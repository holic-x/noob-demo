package com.sz.demo.util;

/**
 * @ClassName ResultCodeEnum
 * @Description 返回参数枚举定义
 * @Author
 * @Date 2020/5/3 18:30
 * @Version
 **/
public enum ResultEnum {

    /**
     * 返回参数枚举定义
     */
    RESPONSE_SUCCESS(0, "[响应成功]"),
    RESPONSE_FAIL(1, "[响应失败]"),


    ERROR(-1,"ERROR [未知处理异常]"),
    // 后台处理相关异常
    BACK_OPER_ERROR(-1001,"BACK OPER ERROR [后台处理异常,请联系管理员进行处理]"),

    SERVER_ERROR(-1001,"SERVER ERROR [服务器响应异常,请联系管理员进行处理]"),
    SYSTEM_ERROR(-1001,"SYSTEM ERROR [系统处理异常]"),
    PARAM_ERROR(-1001,"PARAM ERROR [数据处理异常]"),
    OPER_FAIL_ERROR(-1001,"OPER FAIL ERROR [操作处理失败异常]"),

    // 前后端交互触发异常
    SYSTEM_IDENTIFY_EMPTY_ERROR(-1002,"指定系统标识不能为空"),



    AUTH_ERROR(-2,"AUTH ERROR [用户角色权限相关异常]"),
    LOGIN_PARAM_EMPTY_ERROR(-2001,"登录账号(用户编号)、密码不能为空"),
    LOGIN_DATA_VALID_ERROR(-2001,"登录账号或密码验证失败"),
    NO_USER_DATA_BY_LOCAL(-2001,"当前系统不存在该用户信息,请联系管理员进行处理"),
    NO_AUTH_ERROR(-2001,"当前用户角色权限不足,请联系管理员进行处理"),
    NO_LOGIN_ERROR(-2001,"当前用户尚未登录或登录信息已过期"),
    LOGIN_FAIL_ERROR(-2001,"本地登录认证失败"),

    BUSINESS_EXP(-2,"业务处理异常"),
    FRONT_TRANSFER_DATA_ERROR(-2001,"FRONT_TRANSFER_DATA_ERROR [前端传入数据异常]"),
    OPER_NOT_ALLOW(-2001,"OPER_NOT_ALLOW [当前%s操作不被允许]"),
    NOT_FOUND(-2001,"NOT FOUND [数据不存在或数据为空]"),
    REPEAT_OPER_NOT_ALLOW(-2001,"REPEAT OPER NOT ALLOW [当前系统检验到重复操作,不允许执行]"),

    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
