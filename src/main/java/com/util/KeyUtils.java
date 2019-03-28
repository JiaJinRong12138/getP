package com.util;

public class KeyUtils {

    //生成验证码
    public static String proKey(){
        StringBuffer key = new StringBuffer();
        for(int i = 0; i<6; i++){
            key.append(String.valueOf((int)(Math.random()*10)));
        }
        return key.toString();
    }
}
