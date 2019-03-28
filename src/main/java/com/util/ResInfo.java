package com.util;

import com.enums.Code;

import java.util.Date;

public class ResInfo {
    private int code;
    private String msg;
    private Object data;
    private Date date;

    public ResInfo(Code c) {
        this.code = c.getCode();
        this.msg = c.getMsg();
        this.date = c.getDate();
    }

    public ResInfo(Code c, Object data) {
        this(c);
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object info) {
        this.data = info;
    }
}
