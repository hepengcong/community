package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.practice.community.shiro.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author helium
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

}
