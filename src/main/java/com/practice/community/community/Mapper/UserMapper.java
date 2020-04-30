package com.practice.community.community.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
