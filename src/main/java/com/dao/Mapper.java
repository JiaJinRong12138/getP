package com.dao;

import com.entry.Sinfo;
import com.entry.Temp_info;
import org.apache.ibatis.annotations.*;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    /**
     * 增加 --- 用户报名
     * 删除 --- 在时间段内， 用户撤销报名
     * 修改 --- 在时间段内， 用户修改自己的信息
     * 查下 --- 获取用户相关信息，或者将数据进行这里， 得到报名成功名单
     */
    @Insert("Inster into temp_info values(#{sno}, #{sname}, #{sclass})")
    int add(@Param("sno") String sno, @Param("sname")String sname, @Param("sclass") String sclass);


    //更新Temp表
    @Update("Updata temp_info set sno = #{sno}, sname = #{sname}, sclass = #{sclass}" +
            "where sno = #{sno}")
    int update(@Param("sno") String sno,
               @Param("sname") String sname,
               @Param("sclass") String sclass);

    //查询所有信息
    @Select("select * from sinfo")
    List<Sinfo> select_Sinfo();

    //查询学生的信息（姓名、学号（已知）、班级）
    @Select("Select sno, sname, sclass from temp_info where sno = #{sno}")
    Temp_info selectInfoBySno(@Param("sno") String sno);

    //查询Temp表信息
    @Select("select * from temp_info")
    List<Temp_info> select_Temp();

    //删除
    @Delete("delete from temp_info where sno = #{sno}")
    int delete(@Param("sno") String sno);

}
