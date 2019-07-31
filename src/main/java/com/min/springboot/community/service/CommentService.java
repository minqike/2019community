package com.min.springboot.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.min.springboot.community.dto.CommentDTO;
import com.min.springboot.community.model.Comment;
import com.min.springboot.community.vo.CommentVo;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public interface CommentService extends IService<Comment> {


    Comment save(CommentVo commentVo, HttpServletRequest request);

    CommentDTO getCommnets(Long id);
}
