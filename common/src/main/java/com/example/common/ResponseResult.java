package com.example.common;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private String code;
    private String message;
    /**
     * 返回的数据
     */
    private T data;


    public ResponseResult(String code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public static <T> ResponseResult success(T data){
        return new ResponseResult("000000","成功",data);
    }

    public static <T> ResponseResult fail(String code,String message){
        return new ResponseResult(code,message,null);
    }
}
