package com.entry;

import javax.validation.constraints.NotNull;

public class Sinfo {

    private Integer id;
    private String sno;
    private String spassword;
    private String sname;
    private String sclass;
    private Double sweight;
    private Integer snum;
    private Integer isListen;

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public Double getSweight() {
        return sweight;
    }

    public void setSweight(Double sweight) {
        this.sweight = sweight;
    }

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    public Integer getIsListen() {
        return isListen;
    }

    public void setIsListen(Integer isListen) {
        this.isListen = isListen;
    }

    @Override
    public String toString() {
        return "Sinfo{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", spassword='" + spassword + '\'' +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                ", sweight=" + sweight +
                ", snum=" + snum +
                ", isListen=" + isListen +
                '}';
    }
}
