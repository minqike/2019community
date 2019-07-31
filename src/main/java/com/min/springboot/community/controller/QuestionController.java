package com.min.springboot.community.controller;

import com.min.springboot.community.dto.CommentDTO;
import com.min.springboot.community.error.MyErrorCode;
import com.min.springboot.community.error.MyException;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.CommentService;
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
    private CommentService commentService;

    @Autowired
    private  QuestionService questionService;

    @Autowired
    private UserService userService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model){
        Question question=questionService.getById(id);
        if (question == null ){
            throw  new MyException(MyErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userService.getById(question.getCreator());
        if(user==null){
            user=new User();
            user.setAvatarUrl("");
        }
        //增加view数
        questionService.incView(id);
        model.addAttribute("quser",user);
        model.addAttribute("question",question);

        //获取comment
        CommentDTO commentDTO=commentService.getCommnets(id);
        model.addAttribute("comments",commentDTO);
        return "question";
    }
}
