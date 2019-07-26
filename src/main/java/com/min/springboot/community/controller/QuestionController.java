package com.min.springboot.community.controller;

import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import com.min.springboot.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private  QuestionService questionService;

    @Autowired
    private UserService userService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model){
        Question question=questionService.getById(id);
        User user = userService.getById(question.getCreator());
        if(user==null){
            user=new User();
            user.setAvatarUrl("");
        }
        model.addAttribute("quser",user);
        model.addAttribute("question",question);
        return "question";
    }
}
