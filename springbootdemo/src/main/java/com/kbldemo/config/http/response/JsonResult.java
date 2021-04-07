package com.kbldemo.config.http.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by kbl on 2019/7/4.
 */
public class JsonResult<T> {

    public String JsonSuccessResult(String message) {
        JsonModel model=new JsonModel();
        model.setCode(1);
        model.setSuccess(true);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonSuccessResult(T data) {
        JsonModel model=new JsonModel();
        model.setCode(1);
        model.setSuccess(true);
        model.setData(data);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonSuccessResult(T data,String message) {
        JsonModel model=new JsonModel();
        model.setCode(1);
        model.setSuccess(true);
        model.setData(data);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonErrorResult(String message) {
        JsonModel model=new JsonModel();
        model.setCode(0);
        model.setSuccess(false);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonErrorResult(T data) {
        JsonModel model=new JsonModel();
        model.setCode(0);
        model.setSuccess(false);
        model.setData(data);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonErrorResult(T data,String message) {
        JsonModel model=new JsonModel();
        model.setCode(0);
        model.setSuccess(false);
        model.setData(data);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonErrorResult(Integer code,String message) {
        JsonModel model=new JsonModel();
        model.setCode(0);
        model.setSuccess(false);
        model.setCode(code);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
    public String JsonErrorResult(T data,Integer code,String message) {
        JsonModel model=new JsonModel();
        model.setCode(0);
        model.setSuccess(false);
        model.setData(data);
        model.setCode(code);
        model.setMessage(message);
        return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }
}
