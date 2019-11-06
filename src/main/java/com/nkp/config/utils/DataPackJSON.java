package com.nkp.config.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nkp.pojo.UserInfo;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DataPackJSON {
    int flag;  //0成功  1失败
    String msg;
    int number;
    Map map;

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }





    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public int getFlag() {
        return flag;
    }

    public String getMsg() {
        return msg;
    }


    public int getNumber() {
        return number;
    }
}
