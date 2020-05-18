package com.practice.community.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.community.dto.ReplyDTO;
import com.practice.community.entity.Question;
import com.practice.community.entity.Reply;
import com.practice.community.mapper.QuestionMapper;
import com.practice.community.mapper.ReplyMapper;
import com.practice.community.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReplyService {
    @Resource
    ReplyMapper replyMapper;
    @Resource
    QuestionMapper questionMapper;

    public void insert(Reply reply) {
        reply.setCreateTime(new Date());
        reply.setCreatorId(SecurityUtil.getUserId());
        replyMapper.insert(reply);
    }

    public void delete(Reply reply) {
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.eq("creator_id", SecurityUtil.getUserId())
                .eq("id",reply.getId())
                .eq("question_id", reply.getQuestionId());
        replyMapper.delete(wrapper);
    }


    public List<Reply> listByTime(ReplyDTO reply) {
        Page<Reply> page = new Page<>(reply.getPage(),reply.getSize());
        QueryWrapper<Reply> wrapper=new QueryWrapper<>();

//        replyMapper.listByTime(reply,SecurityUtil.getUserId());
//        BeanUtils.copyProperties(page,iPage);
        List<Reply> list=replyMapper.listByTime(reply,SecurityUtil.getUserId());
        BeanUtils.copyProperties(list,wrapper);
        wrapper.orderByDesc("create_time");
        IPage<Reply> iPage= replyMapper.selectPage(page,wrapper);
        return iPage.getRecords();
    }

    public List<Reply> list(ReplyDTO reply) {
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id",reply.getQuestionId());
        wrapper.orderByAsc("create_time");
        Page<Reply> page = new Page<>(reply.getPage(),reply.getSize());
        IPage<Reply> iPage= replyMapper.selectPage(page,wrapper);
        return iPage.getRecords();
    }
}
