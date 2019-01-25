package com.admin.user.ResultBean;

public enum ResultCode {
    SUCCESS(200,"success"),
    ERROR(101,"系统异常!"),
    PASSWORDERROR(102,"用户名或密码错误!");
    private  int code;
    private String msg;
    ResultCode(int code,String msg){
        this.code=code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
