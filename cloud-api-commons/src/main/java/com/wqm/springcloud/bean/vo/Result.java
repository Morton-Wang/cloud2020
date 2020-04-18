package com.wqm.springcloud.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{

    public final static Integer SUCCESS_CODE = 00;

    private Integer code;
    private String message;
    private T data;

    public Result(Integer code,String message){
        this(code,message,null);
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.code = ResultEnum.SUCESS.getCode();
        result.message = ResultEnum.SUCESS.getMessage();
        result.data = null;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.code = ResultEnum.SUCESS.getCode();
        result.message = ResultEnum.SUCESS.getMessage();
        result.data = data;
        return result;
    }
    public static <T> Result<T> error(ResultEnum resultEnum) {
        Result<T> result = new Result();
        result.code = resultEnum.getCode();
        result.message = resultEnum.getMessage();
        result.data = null;
        return result;
    }

    public static <T> Result<T> error(ResultEnum resultEnum, String message) {
        Result<T> result = new Result();
        result.code = resultEnum.getCode();
        result.message = message;
        result.data = null;
        return result;
    }

    public boolean isSuccess(){
        return SUCCESS_CODE.equals(this.getCode());
    }
}
