package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.service.QuestionService;
import com.practice.community.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public JsonResult index(@RequestParam QuestionDTO quert) {
        return new JsonResult(true, ResultCode.SUCCESS,questionService.list(quert));
    }
}
