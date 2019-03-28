package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;


public class RedisDao {

    @Autowired
    private StringRedisTemplate template;
    private ValueOperations<String, String> ops = template.opsForValue();

    //增加 OR 更新
    public void add(String sno, String sname){
        //向radis内添加数据，一天后过期
        ops.set(sno, sname, 1, TimeUnit.DAYS);
    }

    //删除
    public void delete(String sno){
        ops.getOperations().delete(sno);
    }
    //查找
    public String getBySno(String sno){
        return ops.get(sno);
    }
}
