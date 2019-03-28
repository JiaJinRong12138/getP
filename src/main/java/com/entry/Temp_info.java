package com.entry;

public class Temp_info {
    private String sno;
    private String sname;
    private String sclass;

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

    @Override
    public String toString() {
        return "Temp_info{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }
}
