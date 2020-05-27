package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
@Select("update question set comment_count=comment_count+1 where id = #{questionId}")
    void updateComment(@Param("questionId") Integer questionId);
}
