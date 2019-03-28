package com.service;

import com.dao.Mapper;
import com.entry.LoginUserInfo;
import com.entry.Sinfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_ {
    @Autowired
    private Mapper mapper;

    //登陆
    public Boolean Login(LoginUserInfo lui){

        List<Sinfo> users = mapper.select_Sinfo();
        for(Sinfo u : users){
            if(lui.getSno().equals(u.getSno()) && lui.getSpassword().equals(u.getSpassword())){
                return true;
            }
        }
        return false;
    }

    //是否登陆
    public Boolean isLogined(){
        return false;
    }

    //报名
    public Boolean load(String sno){
        //查找学生的姓名、班级
        System.out.println(mapper.selectInfoBySno(sno));
        return false;
    }

    //撤销报名
    public Boolean exitLoad(){
        return false;
    }

    //获取链接
    public List<String> getLink(){
        return null;
    }

}
