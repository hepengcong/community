package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.entity.SysRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRequestMapper extends BaseMapper<SysRequest> {
    @Select("select * from sys_request_path where #{requestUrl}=url")
    List<SysRequest> findByUrl(@Param("requestUrl") String requestUrl);

}
