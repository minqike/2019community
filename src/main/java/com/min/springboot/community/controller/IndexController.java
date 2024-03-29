package com.min.springboot.community.controller;

import com.github.pagehelper.PageInfo;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping({"/","index"})
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size,
                        Model model) {


        PageInfo<QuestionDTO> pageInfo = questionService.listQuestionDTO(page, size);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("questions", pageInfo.getList());
        model.addAttribute("questionCount", questionService.getQuestionCount());

        return "index";
    }

}
