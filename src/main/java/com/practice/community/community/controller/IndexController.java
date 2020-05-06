package com.practice.community.community.controller;

import com.practice.community.community.dto.QuestionDTO;
import com.practice.community.community.mapper.QuestionMapper;
import com.practice.community.community.mapper.UserMapper;
import com.practice.community.community.model.User;
import com.practice.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model
    ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("token")) {
                    String token = cook.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOS = questionService.list();
        model.addAttribute("questions",questionDTOS);
        return "index";
    }
}
