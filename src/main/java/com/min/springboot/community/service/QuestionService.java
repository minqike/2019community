package com.min.springboot.community.service;

import com.min.springboot.community.model.Question;

import javax.servlet.http.HttpServletRequest;

public interface QuestionService {

    int insert(Question question, HttpServletRequest request);

    int getQuestionCount();
}
