package com.practice.community.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.community.model.Question;
import net.sf.jsqlparser.statement.select.Offset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    @Select("select * from question")
    List<Question> list();

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
