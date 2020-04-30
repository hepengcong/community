package com.practice.community.community.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.community.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
