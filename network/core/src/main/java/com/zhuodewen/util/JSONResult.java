package com.zhuodewen.util;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class JSONResult {
    private boolean success = true;

    private String msg;

    //成功时返回的数据
    private Object result;

    public JSONResult mark(String msg) {
        this.msg = msg;
        this.success = false;
        return this;
    }
}
