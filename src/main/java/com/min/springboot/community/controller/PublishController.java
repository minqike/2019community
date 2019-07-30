package com.min.springboot.community.controller;

import com.min.springboot.community.model.Question;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private  QuestionService questionService;

    @GetMapping("/question/publish")
    public String publish(Model model){
        model.addAttribute("question", new Question());
        return "publish";

    }

    @GetMapping("/question/publish/{id}")
    public String edit(@PathVariable Long id,
                       Model model){
        Question question = questionService.getById(id);
        model.addAttribute("question",question);
        return "publish";

    }

    @PostMapping("/question/publish")
    public String doPublish(
            Question question,
            Model model,
            HttpServletRequest request) throws Exception {


        questionService.insert(question,request);
        return "redirect:/";
    }
}
