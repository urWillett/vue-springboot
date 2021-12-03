package com.example.springboot1.common;
//返回前台数据的包装类
public class Result <T>{
    private String code;//后台告诉前台 这次返回的数据是成功还是失败  ，成功为0
    private String msg;//后台把错误信息返回给前台，用户名或者密码错误
    private T data;//T 泛型 表示任何一种数据类型都可以被result所包含

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Result() {}

    public Result(T data){this.data= data;}

    public static Result success(){
        Result result =new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T>  success(T data){
        Result<T> result =new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result error(String code,String msg){
        Result result =new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
