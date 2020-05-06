package com.practice.community.community.service;

import com.practice.community.community.dto.PaginationDTO;
import com.practice.community.community.dto.QuestionDTO;
import com.practice.community.community.mapper.QuestionMapper;
import com.practice.community.community.mapper.UserMapper;
import com.practice.community.community.model.Question;
import com.practice.community.community.model.User;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer size, Integer page) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, page);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.finById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,size,page);
        return paginationDTO;
    }
}


