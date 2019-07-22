package com.min.springboot.community.controller;

import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.mapper.UserMapper;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionList = questionService.listQuestionDTO();
        model.addAttribute("questions",questionList);
        model.addAttribute("questionCount",questionService.getQuestionCount());

        return "index";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }



}
