package com.xzit.simpleftp.utils;

import com.alibaba.fastjson.JSONObject;
import com.xzit.simpleftp.entity.FileInfo;

import java.util.Map;

public class ResultUtil {

   private  Integer code;
   private  String msg;
   private  FileInfo data;

    public ResultUtil(FileInfo data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    public ResultUtil(Integer code , String msg) {
        this.code = code;
        this.msg = msg;

    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FileInfo getData() {
        return data;
    }

    public void setData(FileInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code + '\n' +
                ", msg=" + msg + '\n' +
                ", data={" + data.getId() + '\n' +
                data.getSize()+ '\n' +
                data.getType()+ '\n' +
                data.getOriginalfilename()+ '\n' +
                data.getCreatetime()+ '\n' +
                data.getFilepath()+ '\n' +
                '}'+'\n' +
                '}';
    }
}
