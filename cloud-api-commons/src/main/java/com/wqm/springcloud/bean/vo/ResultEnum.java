package com.wqm.springcloud.bean.vo;

/**
 * 公用消息码
 *
 */
public enum ResultEnum {
    AUTH_EXCEPTION(401,"授权失败"),
    FORBINDDEN(403,"不允许访问"),

    SUCESS(00, "操作成功"),
    EXCEPTION(01,"系统异常"),
    PARAM_EXCEPTION(02,"请求参数异常"),
    BUSINESS_EXCEPTION(03,"业务异常"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}