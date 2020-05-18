package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.service.QuestionService;
import com.practice.community.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    QuestionService questionService;

    @RequestMapping("/index")
    public JsonResult index(@RequestBody QuestionDTO query) {
        return new JsonResult(true, ResultCode.SUCCESS,questionService.list(query));
    }


}
