package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.entity.Question;
import com.practice.community.service.QuestionService;
import com.practice.community.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PublishController {
    @Resource
    QuestionService questionService;

    @RequestMapping("/published")
    public JsonResult publish(@RequestBody QuestionDTO query) {

//        return new JsonResult(true, ResultCode.SUCCESS, questionService.listById(SecurityUtil.getUserId(), query));
        return null;
    }

    @PostMapping("/publish")
    public JsonResult doPublish(@RequestBody Question question
    ) {
        questionService.insert(question);
        return new JsonResult(true, ResultCode.SUCCESS);
    }


}
