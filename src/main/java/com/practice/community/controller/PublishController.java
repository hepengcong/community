package com.practice.community.controller;

import com.practice.community.mapper.QuestionMapper;
import com.practice.community.mapper.UserMapper;
import com.practice.community.model.Question;
import com.practice.community.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PublishController {
    @Resource
    QuestionMapper questionMapper;
    @Resource
    UserMapper userMapper;

    @GetMapping("/published")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam Question question
    ) {
        if (user == null) {
            model.addAttribute("error", "out of login");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);
        return "redirect:/";
    }
}
