package com.min.springboot.community.controller;

import com.min.springboot.community.dto.R;
import com.min.springboot.community.error.MyException;
import com.min.springboot.community.model.Comment;
import com.min.springboot.community.service.CommentService;
import com.min.springboot.community.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/comment/save")
    @ResponseBody
    public R saveComment(@RequestBody CommentVo commentVo,
                         HttpServletRequest request){
        if (request.getSession().getAttribute("user")==null){
            return R.error(-1, "没有登录");
        }

        try {
            Comment comment = commentService.save(commentVo, request);
            return R.ok(comment);
        }catch (MyException e){
            return R.error(e.getCode(), e.getMessage());
        }
    }

}
