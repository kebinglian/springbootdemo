package com.kbldemo.config.http.response;


/**
 * Created by luosenchuan on 2019/7/4.
 */
public class JsonModel<T> {
    //数据对象泛型
    private T data;
    //状态代码-3：不安全参数 -2：未授权 -1：未登陆 0：失败 1：成功  400,404,500等错误
    public int code;
    //是否成功
    public boolean success;
    //状态消息
    public String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
