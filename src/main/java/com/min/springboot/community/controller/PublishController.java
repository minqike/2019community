package com.min.springboot.community.controller;

import com.min.springboot.community.model.Question;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private  QuestionService questionService;

    @GetMapping("/question/publish")
    public String publish(){

        return "publish";

    }

    @PostMapping("/question/publish")
    public String doPublish(
            Question question,
            Model model,
            HttpServletRequest request){
        

        questionService.insert(question,request);
        return "redirect:/";
    }
}
