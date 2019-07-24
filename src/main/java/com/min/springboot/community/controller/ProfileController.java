package com.min.springboot.community.controller;

import com.github.pagehelper.PageInfo;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/questions")
    public String myquestions(Model model,
                              @RequestParam(defaultValue = "1",required = false) int page,
                              @RequestParam(defaultValue = "5",required = false) int size,
                              HttpServletRequest request
            ){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            Long uid=user.getId();
            PageInfo<QuestionDTO> pageInfo = questionService.listMyQuestionDTO(page, size, uid);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("questions", pageInfo.getList());
            return "/profile/myquestions";
        }
        return "/";
    }
}
