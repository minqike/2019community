package com.min.springboot.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService extends IService<Question> {

    int insert(Question question, HttpServletRequest request);

    int getQuestionCount();
    PageInfo<QuestionDTO> listQuestionDTO(int page,int size);
}
