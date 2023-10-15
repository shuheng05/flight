package com.aurora.mapper;

import com.aurora.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where name = #{name}")
    public Admin getByName(String name);

    @Update("update admin set password = #{password} WHERE name = #{name}")
    public void update(String name, String password);
}
