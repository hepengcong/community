package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;
import com.practice.community.config.annotation.AnonymousAccess;
import com.practice.community.dto.QuestionDTO;
import com.practice.community.service.QuestionService;
import com.practice.community.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    @Autowired
    QuestionService questionService;

    @RequestMapping("/index")
    public JsonResult index(@RequestBody QuestionDTO query) {
        return new JsonResult(true, ResultCode.SUCCESS,questionService.list(query));
    }

    @GetMapping("/question/detail")
    public JsonResult detail(@RequestParam Integer id){
        return new JsonResult(true,ResultCode.SUCCESS,questionService.selectById(id));
    }

    @RequestMapping("/question/hot")
    public JsonResult hotQuestion(){
        return new JsonResult(true,ResultCode.SUCCESS,questionService.getHot());
    }

}
