package com.web;

import com.entry.LoginUserInfo;
import com.entry.Temp_info;
import com.enums.Code;
import com.service.Service_;
import com.util.ResInfo;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Controller
@RequestMapping("/test")
public class API {

    @Autowired
    private Service_ service_;

    @Autowired
    private StringRedisTemplate srt;

    @RequestMapping("/t")
    public String  test(){

        Temp_info ti  = new Temp_info();
        ti.setSno("41700749");
        ti.setSname("欧阳铁柱");
        ti.setSclass("07");
        srt.opsForValue().set("sno", String.valueOf(ti));
        return srt.opsForValue().get("sno");
    }

    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResInfo login(@RequestBody @Valid LoginUserInfo lui){
        if((lui.getToken() == null) && (service_.Login(lui))) {
            String token = TokenUtil.generateToken(lui.getSno());
            System.out.println(token+"\n++++++"+TokenUtil.getUserIdFromRequest(token));
            lui.setToken(token);
            lui.setSpassword(null);
            return new ResInfo(Code.SUCCESS, lui);
        }else if(lui.getToken() != null){
            //验证token
            if(TokenUtil.verify(lui.getToken())){
                lui.setSpassword(null);
                return new ResInfo(Code.ISLOGINED, lui);
            }else{
                lui.setSpassword(null);
                return new ResInfo(Code.TOKENERROR, lui);
            }
        }
        lui.setSpassword(null);
        return new ResInfo(Code.ERROR, lui);
    }


    //提交报名
    @RequestMapping(value = "/singup", method = RequestMethod.POST)
    @ResponseBody
    public ResInfo SignUp(@RequestBody @Valid LoginUserInfo lui){

        //根据token获取学号
        /*String token = lui.getToken();
        if(lui != null && TokenUtil.verify(token)) {
            //判断Token是否合法，合法则获取sno
            String sno = TokenUtil.getSno(TokenUtil.getSno(token));
            //
            System.out.println("++++++++++++++++++++" + sno);
            service_.load(sno);


        }
*/
        //提交学号
        return null;
    }
}

