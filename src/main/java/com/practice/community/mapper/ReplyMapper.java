package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.dto.ReplyDTO;
import com.practice.community.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
    @Select("select * from reply r left join question q on creator =#{id} and r.question_id =q.id")
    List<Reply> listByTime(ReplyDTO reply, @Param("id") Integer userId);
}
