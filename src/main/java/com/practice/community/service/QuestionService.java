package com.practice.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.mapper.QuestionMapper;
import com.practice.community.entity.Question;
import com.practice.community.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    public List<Question> list(QuestionDTO query) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        Page<Question> page = new Page<>(query.getPage(), query.getSize());
        IPage<Question> iPage= questionMapper.selectPage(page,wrapper);
        return iPage.getRecords();
    }

    public void insert(Question question) {
        if(SecurityUtil.getUserId()==null||SecurityUtil.getUserId().equals(""))
            throw new RuntimeException("没有登录");
        question.setCreator(SecurityUtil.getUserId());

        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);
    }

    public List<Question> listById(Integer userId,QuestionDTO query) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("creator",userId);
        Page<Question> page = new Page<>(query.getPage(), query.getSize());
        IPage<Question> iPage= questionMapper.selectPage(page,wrapper);
        return iPage.getRecords();
    }
}


