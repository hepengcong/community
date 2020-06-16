package com.practice.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.entity.Question;
import com.practice.community.mapper.QuestionMapper;
import com.practice.community.util.RedisUtil;
import com.practice.community.util.SecurityUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QuestionService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private QuestionMapper questionMapper;


    @Cacheable(value = "List<Question>",unless = "#result eq null ")
    public List<Question> list(QuestionDTO query) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        Page<Question> page = new Page<>(query.getPage(), query.getSize());
        IPage<Question> iPage = questionMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    public void insert(Question question) {
        if (SecurityUtil.getUserId() == null || SecurityUtil.getUserId().equals(""))
            throw new RuntimeException("没有登录");
        question.setCreator(SecurityUtil.getUserId());

        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);
    }

    @Cacheable(value = "List<Question>",unless = "#result eq null ")
    public List<Question> listById(Integer userId, QuestionDTO query) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("creator", userId);
        Page<Question> page = new Page<>(query.getPage(), query.getSize());
        IPage<Question> iPage = questionMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    @Cacheable(value = "Question",unless = "#result eq null ")
    public Question selectById(Integer id) {
        redisUtil.zincr(id);
        return questionMapper.selectById(id);
    }

    public List<Question> getHot() {
        List<Question> list = new ArrayList<>();
        Iterator i = redisUtil.zRevRank();
        while (i.hasNext()) {
            ZSetOperations.TypedTuple<Object> item = (ZSetOperations.TypedTuple<Object>) i.next();
            list.add(questionMapper.selectById((Serializable) item.getValue()));
        }

        return list;
    }
}


