package com.admin.user.ResultBean;

import lombok.Data;
 @Data
public class ResultData<T> {
    private int code;
    private String msg;
    private T data;
    public ResultData(ResultCode code){
        this(code,null);
    }
    public ResultData(ResultCode resultCode,T data){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data=data;
     }
    public ResultData(int code,String msg){
        this.code=code;
        this.msg = msg;
    }
    public ResultData(int code,T data){
        this.code=code;
        this.data = data;
    }
    public ResultData(int code,String msg,T data){
        this.code=code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> ResultData<T> success(T data){
        return new ResultData<T>(200,data);
    }
    public static ResultData failure(){
        return new ResultData(101,"请求失败");
    }
}
